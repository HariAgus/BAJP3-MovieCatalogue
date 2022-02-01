package com.hariagus.finalproject.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hariagus.finalproject.ui.movie.MovieFragment
import com.hariagus.finalproject.ui.tvshow.TvShowFragment

class HomeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragment: List<Fragment> = listOf(
        MovieFragment(),
        TvShowFragment()
    )

    override fun getItemCount(): Int = fragment.size

    override fun createFragment(position: Int): Fragment = fragment[position]

}