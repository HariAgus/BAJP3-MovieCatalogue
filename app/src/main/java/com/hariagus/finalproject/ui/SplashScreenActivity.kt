package com.hariagus.finalproject.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.hariagus.finalproject.R
import com.hariagus.finalproject.ui.home.HomeActivity
import com.hariagus.finalproject.utils.Const.Companion.DELAY_MOVE

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, DELAY_MOVE)
    }
}