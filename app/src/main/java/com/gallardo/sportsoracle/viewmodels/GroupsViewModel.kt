package com.gallardo.sportsoracle.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gallardo.sportsoracle.data.SportsOracleRepository
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase.Companion.getDatabase
import com.gallardo.sportsoracle.model.Group
import com.gallardo.sportsoracle.data.network.FootballApi
import com.gallardo.sportsoracle.model.Team
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import java.io.IOException


class GroupsViewModel(application: Application) : AndroidViewModel(application) {

    private val sportsOracleRepository = SportsOracleRepository(getDatabase(application))

    init {
        refreshDataFromRepository()
    }

    val groups : List<Group> = sportsOracleRepository.getGroups()

    fun getGroupTeams(groupKey: String) : List<Team> {
        return sportsOracleRepository.getGroupTeams(groupKey)
    }

    private fun refreshDataFromRepository() {
        runBlocking {
                sportsOracleRepository.refreshDatabase()
        }
    }
}