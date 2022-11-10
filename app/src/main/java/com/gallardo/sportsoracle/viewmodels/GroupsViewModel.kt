package com.gallardo.sportsoracle.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.gallardo.sportsoracle.data.SportsOracleRepository
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase.Companion.getDatabase
import kotlinx.coroutines.runBlocking


class GroupsViewModel(application: Application) : AndroidViewModel(application) {

    private val sportsOracleRepository = SportsOracleRepository(getDatabase(application))

    val groups = sportsOracleRepository.getGroups()

    val teamWithGroupResults = sportsOracleRepository.getTeamsWithGroupResults()

    private fun refreshDataFromRepository() {
        runBlocking {
            sportsOracleRepository.refreshDatabase()
        }
    }
}