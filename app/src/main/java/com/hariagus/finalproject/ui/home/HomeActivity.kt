package com.hariagus.finalproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.hariagus.finalproject.databinding.ActivityHomeBinding
import com.hariagus.finalproject.ui.favorite.FavoriteActivity
import com.hariagus.finalproject.utils.startActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = HomeViewPagerAdapter(this, supportFragmentManager)
        binding.homeViewPager.adapter = viewPagerAdapter
        tabLayoutHome.setupWithViewPager(binding.homeViewPager)

        binding.ivChangeLanguage.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }

        binding.ivFavoriteActivity.setOnClickListener {
            startActivity<FavoriteActivity>()
        }

    }
}