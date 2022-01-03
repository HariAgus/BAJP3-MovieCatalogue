package com.hariagus.finalproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.hariagus.finalproject.R
import com.hariagus.finalproject.ui.favorite.FavoriteActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewPagerAdapter = HomeViewPagerAdapter(this, supportFragmentManager)
        homeViewPager.adapter = viewPagerAdapter
        tabLayoutHome.setupWithViewPager(homeViewPager)

        ivChangeLanguage.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }

        ivFavoriteActivity.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }

    }
}