package com.hariagus.finalproject.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hariagus.finalproject.ui.favorite.movie.FavoriteMovieFragment
import com.hariagus.finalproject.ui.favorite.tvshow.FavoriteTvShowFragment

class FavoriteViewPageAdapter(fManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fManager, lifecycle) {

    private val fragment: List<Fragment> = listOf(
        FavoriteMovieFragment(),
        FavoriteTvShowFragment()
    )

    override fun getItemCount(): Int = fragment.size

    override fun createFragment(position: Int): Fragment = fragment[position]
}