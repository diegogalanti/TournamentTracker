package com.gallardo.sportsoracle.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase
import com.gallardo.sportsoracle.data.network.FootballApi
import com.gallardo.sportsoracle.model.Group
import com.gallardo.sportsoracle.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class SportsOracleRepository(private val database: SportsOracleDatabase) {
    fun getGroups(): List<Group> {
        return database.sportsOracleDao.getGroups()
    }

    fun getGroupTeams(groupKey: String): List<Team> {
        return database.sportsOracleDao.getGroupTeams(groupKey)
    }

    suspend fun refreshDatabase() {
        withContext(Dispatchers.IO) {
            try {


                refreshGroups()
                refreshTeams()
                refreshStadiums()
                refreshSquads()
                refreshMatches()
                refreshCards()
                refreshGoals()
            }
            catch (e: Exception){
                Log.e("Error",e.toString())
            }
        }
    }

    private suspend fun refreshGroups() {
        val groups = FootballApi.retrofitService.getGroups().items
        database.sportsOracleDao.insertGroups(groups)
    }

    private suspend fun refreshCards() {
        val cards = FootballApi.retrofitService.getCards().items
        database.sportsOracleDao.insertCards(cards)
    }

    private suspend fun refreshGoals() {
        val goals = FootballApi.retrofitService.getGoals().items
        database.sportsOracleDao.insertGoals(goals)
    }

    private suspend fun refreshMatches() {
        val matches = FootballApi.retrofitService.getMatches().items
        database.sportsOracleDao.insertMatches(matches)
    }

    private suspend fun refreshSquads() {
        val squads = FootballApi.retrofitService.getSquads().items
        database.sportsOracleDao.insertSquads(squads)
    }

    private suspend fun refreshTeams() {
        val teams = FootballApi.retrofitService.getTeams().items
        database.sportsOracleDao.insertTeams(teams)
    }

    private suspend fun refreshStadiums() {
        val stadiums = FootballApi.retrofitService.getStadiums().items
        database.sportsOracleDao.insertStadiums(stadiums)
    }
}