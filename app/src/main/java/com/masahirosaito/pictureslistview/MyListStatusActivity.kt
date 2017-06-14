package com.masahirosaito.pictureslistview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.masahirosaito.pictureslistview.adapter.MyListStatusAdapter
import com.masahirosaito.pictureslistview.client.MyListStatusesClient
import com.masahirosaito.pictureslistview.model.MyList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

class MyListStatusActivity : AppCompatActivity() {
    private val consumerKey: String = ""
    private val consumerSecret: String = ""
    private val accessToken: String = ""
    private val accessTokenSecret: String = ""

    val myListStatusAdapter: MyListStatusAdapter by lazy {
        MyListStatusAdapter(applicationContext)
    }

    companion object {
        private const val MY_LIST_EXTRA: String = "my_list"

        fun intent(context: Context, myList: MyList): Intent =
                Intent(context, MyListStatusActivity::class.java)
                        .putExtra(MY_LIST_EXTRA, myList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myliststatus)

        (findViewById(R.id.my_list_status_view) as ListView).apply {
            this.adapter = myListStatusAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        val consumer = OkHttpOAuthConsumer(consumerKey, consumerSecret).apply {
            setTokenWithSecret(accessToken, accessTokenSecret)
        }

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
                .addInterceptor(SigningInterceptor(consumer))
                .addInterceptor(logging)
                .build()

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.twitter.com/1.1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        retrofit.create(MyListStatusesClient::class.java).apply {
            val id = intent.getParcelableExtra<MyList>(MY_LIST_EXTRA).id.apply {
                Log.d("MyListStatus", this.toString())
            }
            getMyListStatuses(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        myListStatusAdapter.myListStatuses = it
                        myListStatusAdapter.notifyDataSetChanged()
                    }, {
                        toast("ERROR: $it")
                        Log.d("MyListStatus", it.message)
                        it.printStackTrace()
                    })
        }
    }
}