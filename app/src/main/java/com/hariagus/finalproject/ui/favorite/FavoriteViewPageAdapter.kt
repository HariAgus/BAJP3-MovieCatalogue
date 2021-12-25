package com.hariagus.finalproject.ui.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hariagus.finalproject.R
import com.hariagus.finalproject.ui.favorite.movie.FavoriteMovieFragment
import com.hariagus.finalproject.ui.favorite.tvshow.FavoriteTvShowFragment

class FavoriteViewPageAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val tabsListFavoriteOf = intArrayOf(
        R.string.favorite_movie,
        R.string.favorite_tvShow
    )

    private val fragment: List<Fragment> = listOf(
        FavoriteMovieFragment(),
        FavoriteTvShowFragment()
    )

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(tabsListFavoriteOf[position])
    }

    override fun getCount(): Int = tabsListFavoriteOf.size

    override fun getItem(position: Int): Fragment = fragment[position]
}