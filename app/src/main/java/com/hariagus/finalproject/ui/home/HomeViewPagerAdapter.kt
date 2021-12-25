package com.hariagus.finalproject.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hariagus.finalproject.R
import com.hariagus.finalproject.ui.movie.MovieFragment
import com.hariagus.finalproject.ui.tvshow.TvShowFragment

class HomeViewPagerAdapter(private val context: Context, fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabListOf = intArrayOf(
        R.string.movie_title,
        R.string.tvShow_title
    )

    private val fragment: List<Fragment> = listOf(
        MovieFragment(),
        TvShowFragment()
    )

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(tabListOf[position])
    }

    override fun getCount(): Int = tabListOf.size

    override fun getItem(position: Int): Fragment = fragment[position]
}