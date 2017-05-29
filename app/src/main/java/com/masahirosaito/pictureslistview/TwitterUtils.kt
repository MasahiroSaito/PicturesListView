package com.masahirosaito.pictureslistview

import android.annotation.SuppressLint
import android.content.Context
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken


class TwitterUtils {

    companion object {
        private val TOKEN = "token"
        private val TOKEN_SECRET = "secret_token"
        private val PREF_NAME = "twitter_access_token"

        fun getTwitterInstance(context: Context): Twitter {
            val consumerKey = context.getString(R.string.twitter_consumer_key)
            val consumerSecret = context.getString(R.string.twitter_consumer_secret)

            val factory = TwitterFactory()
            val twitter = factory.instance
            twitter.setOAuthConsumer(consumerKey, consumerSecret)

            if (hasAccessToken(context)) {
                twitter.oAuthAccessToken = loadAccessToken(context)
            }
            return twitter
        }

        @SuppressLint("ApplySharedPref")
        fun storeAccessToken(context: Context, accessToken: AccessToken) {
            val preferences = context.getSharedPreferences(PREF_NAME,
                    Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(TOKEN, accessToken.token)
            editor.putString(TOKEN_SECRET, accessToken.tokenSecret)
            editor.commit()
        }

        fun loadAccessToken(context: Context): AccessToken? {
            val preferences = context.getSharedPreferences(PREF_NAME,
                    Context.MODE_PRIVATE)
            val token = preferences.getString(TOKEN, null)
            val tokenSecret = preferences.getString(TOKEN_SECRET, null)
            if (token != null && tokenSecret != null) {
                return AccessToken(token, tokenSecret)
            } else {
                return null
            }
        }

        fun hasAccessToken(context: Context): Boolean {
            return loadAccessToken(context) != null
        }
    }
}