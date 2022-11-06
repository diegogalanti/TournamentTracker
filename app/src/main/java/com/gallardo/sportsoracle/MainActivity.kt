package com.gallardo.sportsoracle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import com.gallardo.sportsoracle.data.SportsOracleRepository
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
        createImageLoader()
        runBlocking {
            SportsOracleRepository(SportsOracleDatabase.getDatabase(application)).refreshDatabase()
        }
    }

    private fun createImageLoader() {
        val imageLoader = this.let {
            ImageLoader.Builder(it).components {
                add(SvgDecoder.Factory())
            }.placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
                .diskCache {
                    DiskCache.Builder()
                        .directory(it.cacheDir.resolve("image_cache"))
                        .maxSizePercent(0.1)
                        .build()
                }
                .build()
        }
        Coil.setImageLoader(imageLoader)
    }

    fun expandCard(arrow: View) {
        val parent = arrow.parent as ViewGroup
        val hiddenGroup = parent.findViewById<View>(R.id.expand_group)
        if (hiddenGroup.visibility == View.VISIBLE) {
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