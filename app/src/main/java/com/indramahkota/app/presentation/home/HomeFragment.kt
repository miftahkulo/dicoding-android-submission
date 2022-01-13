package com.indramahkota.app.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.indramahkota.app.databinding.FragmentHomeBinding
import com.indramahkota.app.viewmodel.SharedViewModel
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.common.utils.navigateSafe

class HomeFragment : BaseBindingFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
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
        }
    }

    override fun unbindFragment() {
        _binding = null
    }

}