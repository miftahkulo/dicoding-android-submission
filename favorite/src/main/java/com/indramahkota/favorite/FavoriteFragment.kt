package com.indramahkota.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.indramahkota.app.viewmodel.SharedViewModel
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.favorite.adapter.FavoritePagerAdapter
import com.indramahkota.favorite.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseBindingFragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.isDetailPage.value = false

        val pagerAdapter = FavoritePagerAdapter(this)

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
        binding.pager.adapter = null
        _binding = null
    }
}