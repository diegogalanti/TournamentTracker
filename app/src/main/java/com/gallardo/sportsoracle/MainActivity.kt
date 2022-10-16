package com.gallardo.sportsoracle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gallardo.sportsoracle.viewmodels.GroupsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

val groupsViewModel = GroupsViewModel()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        bottomNavigationView.setupWithNavController(navController)
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.groupsFragment, R.id.matchesFragment, R.id.bracketFragment, R.id.liveFragment))
//        setupActionBarWithNavController(navController, appBarConfiguration)
        groupsViewModel.groups
    }
}