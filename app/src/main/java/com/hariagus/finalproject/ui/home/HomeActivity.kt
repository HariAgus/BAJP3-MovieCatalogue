package com.hariagus.finalproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.hariagus.finalproject.R
import com.hariagus.finalproject.databinding.ActivityHomeBinding
import com.hariagus.finalproject.ui.favorite.FavoriteActivity
import com.hariagus.finalproject.utils.startActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabLayout()

        binding.ivChangeLanguage.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }

        binding.ivFavoriteActivity.setOnClickListener {
            startActivity<FavoriteActivity>()
        }

    }

    private fun setupTabLayout() {
        val viewPagerAdapter = HomeViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.homeViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.tabLayoutHome,
            binding.homeViewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.movie_title)
                1 -> tab.text = getString(R.string.tvShow_title)
            }
        }.attach()
    }
}