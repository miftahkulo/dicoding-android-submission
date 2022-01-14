package com.indramahkota.favorite.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.indramahkota.favorite.FavoriteListFragment
import com.indramahkota.favorite.IS_TV_ARG

private const val NUM_PAGES = 2

class FavoritePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment = when (position) {
        1 -> FavoriteListFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_TV_ARG, false)
            }
        }
        else -> FavoriteListFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_TV_ARG, true)
            }
        }
    }
}