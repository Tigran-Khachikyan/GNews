package com.gnews.ui.main

import android.os.Bundle
import android.widget.ProgressBar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gnews.R
import com.gnews.databinding.ActivityMainBinding
import com.gnews.ui.BaseActivity
import com.gnews.utils.visibleOrGone

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    override val progressBar: ProgressBar
        get() = binding.progressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_search, R.id.nav_favourite
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleBottomNavViewVisibility(destination.id)
        }
    }

    private fun handleBottomNavViewVisibility(id: Int) {
        binding.navView.visibleOrGone(id != R.id.nav_details)
    }
}
