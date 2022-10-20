package com.gallardo.sportsoracle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }

    fun expandCard(arrow: View) {
        val parent = arrow.parent as ViewGroup
        val hiddenGroup = parent.findViewById<View>(R.id.expand_group)


            if (hiddenGroup.visibility === View.VISIBLE) {
//                TransitionManager().beginDelayedTransition(cardView, AutoTransition())
                hiddenGroup.visibility = View.GONE
                (arrow as MaterialButton).setIconResource(R.drawable.ic_expand_more_24)
            } else {
//                TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                hiddenGroup.visibility = View.VISIBLE
                (arrow as MaterialButton).setIconResource(R.drawable.ic_expand_less_24)
            }

    }
}