package com.indramahkota.app.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.indramahkota.app.R
import com.indramahkota.app.databinding.FragmentHomeBinding
import com.indramahkota.app.viewmodel.MovieViewModel
import com.indramahkota.app.viewmodel.SharedViewModel
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.common.utils.navigateSafe

class HomeFragment : BaseBindingFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private val movieViewModel: MovieViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        initView()
        observeViewModel()
    }

    private fun initView() {
        with(binding) {
            val navHostFragment = childFragmentManager.findFragmentById(
                navHostContainerSecondary.id
            ) as NavHostFragment

            navController = navHostFragment.navController
            navController.navigateSafe(sharedViewModel.previousHomeStateFragment)

            bottomNavView.setOnItemSelectedListener { item ->
                sharedViewModel.previousHomeStateFragment = item.itemId
                if (item.itemId and bottomNavView.selectedItemId == 0) onNavDestinationSelected(
                    item,
                    navController
                )
                true
            }

            bottomNavView.setupWithNavController(navController)

            incHeader.backBtn.setOnClickListener {
                if (sharedViewModel.isDetailPage.value == true) {
                    navController.navigateUp()
                }
            }

            incHeader.favoriteBtn.setOnClickListener {
                val movie = sharedViewModel.isMovieFavorite.value?.copy()
                if (movie != null) {
                    movie.favorite = !movie.favorite
                    movieViewModel.setFavorite(movie, movie.favorite)
                    sharedViewModel.isMovieFavorite.value = movie
                }
            }
        }
    }

    private fun observeViewModel() {
        sharedViewModel.isDetailPage.observe(this) {
            if (it) {
                binding.incHeader.backBtn.visibility = View.VISIBLE
                binding.incHeader.favoriteBtn.visibility = View.VISIBLE
            } else {
                binding.incHeader.backBtn.visibility = View.GONE
                binding.incHeader.favoriteBtn.visibility = View.GONE
            }
        }

        sharedViewModel.isMovieFavorite.observe(this) {
            if (it?.favorite == true) {
                binding.incHeader.favoriteBtn.setImageDrawable(
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_red_24dp, null)
                )
            } else {
                binding.incHeader.favoriteBtn.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_baseline_favorite_border_24,
                        null
                    )
                )
            }
        }
    }

    override fun unbindFragment() {
        _binding = null
    }

}