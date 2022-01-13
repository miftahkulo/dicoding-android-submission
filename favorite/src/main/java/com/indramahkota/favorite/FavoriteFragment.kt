package com.indramahkota.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.favorite.databinding.FragmentFavoriteBinding

private const val NUM_PAGES = 2

class FavoriteFragment : BaseBindingFragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        val pagerAdapter = ScreenSlidePagerAdapter(this)

        with(binding) {
            pager.adapter = pagerAdapter
            TabLayoutMediator(tab, pager) { tab, position ->
                when (position) {
                    1 -> tab.text = "Movie"
                    else -> tab.text = "Tv"
                }
            }.attach()
        }
    }

    override fun unbindFragment() {
        _binding = null
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
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
}