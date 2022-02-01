package com.hariagus.finalproject.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.hariagus.finalproject.R
import com.hariagus.finalproject.databinding.ActivityFavoriteBinding
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = FavoriteViewPageAdapter(supportFragmentManager, lifecycle)
        favoriteViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.tabLayoutFavorite,
            binding.favoriteViewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.favorite_movie)
                1 -> tab.text = getString(R.string.favorite_tvShow)
            }
        }.attach()
    }
}