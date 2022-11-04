package com.gallardo.sportsoracle.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.gallardo.sportsoracle.data.SportsOracleRepository
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase.Companion.getDatabase
import com.gallardo.sportsoracle.model.Goal
import com.gallardo.sportsoracle.model.Group
import com.gallardo.sportsoracle.model.Match
import com.gallardo.sportsoracle.model.Team
import kotlinx.coroutines.runBlocking


class GroupsViewModel(application: Application) : AndroidViewModel(application) {

    private val sportsOracleRepository = SportsOracleRepository(getDatabase(application))

    init {
        refreshDataFromRepository()
    }

    val groups : List<Group> = sportsOracleRepository.getGroups()

    fun getGroupTeamsFlags(groupKey: String) : List<String> {
        return sportsOracleRepository.getGroupTeamsFlags(groupKey)
    }

    fun getGroupTeams(groupKey: String) : List<Team> {
        return sportsOracleRepository.getGroupTeams(groupKey)
    }

    fun getGroupMatches(groupKey: String) : List<Match> {
        return sportsOracleRepository.getGroupMatches(groupKey)
    }

    fun getGroupMatchesAndGoal(groupKey: String) : Map<Match, List<Goal>> {
        return sportsOracleRepository.getGroupMatchesAndGoals(groupKey)
    }

    private fun refreshDataFromRepository() {
        runBlocking {
                sportsOracleRepository.refreshDatabase()
        }
    }
}