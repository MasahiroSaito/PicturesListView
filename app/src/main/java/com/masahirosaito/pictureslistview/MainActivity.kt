package com.masahirosaito.pictureslistview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!TwitterUtils.hasAccessToken(this)) {
            val intent = Intent(this, TwitterOAuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
