package com.hariagus.finalproject.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hariagus.finalproject.R
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val viewPagerAdapter = FavoriteViewPageAdapter(this, supportFragmentManager)
        favoriteViewPager.adapter = viewPagerAdapter
        tabLayoutFavorite.setupWithViewPager(favoriteViewPager)
    }
}