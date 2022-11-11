package com.gallardo.sportsoracle.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase
import com.gallardo.sportsoracle.data.network.FootballApi
import com.gallardo.sportsoracle.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class SportsOracleRepository(private val database: SportsOracleDatabase) {
    fun getGroups(): LiveData<Map<Group, List<TeamWithGroupResult>>> {
        return database.sportsOracleDao.getGroups()
    }

    fun getGroupTeamsFlags(groupKey: String): LiveData<List<String>> {
        return database.sportsOracleDao.getGroupTeamsFlags(groupKey)
    }

    fun getGroupTeams(groupKey: String): List<Team> {
        return database.sportsOracleDao.getGroupTeams(groupKey)
    }

    fun getGroupMatchesAndGoals(groupKey: String): Map<Match, List<Goal>> {
        return database.sportsOracleDao.getGroupMatchesAndGoals(groupKey)
    }

    fun getMatches(date: String): List<Match> {
        return database.sportsOracleDao.getMatches(date)
    }

    fun getTeams(): List<Team> {
        return database.sportsOracleDao.getTeams()
    }

    fun getMatchesDates(): List<String> {
        return database.sportsOracleDao.getMatchesDates()
    }

    fun getTeamsWithGroupResults(): LiveData<List<TeamWithGroupResult>> {
        return database.sportsOracleDao.getTeamsWithGroupResults()
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
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

    suspend fun refreshGroupsDatabase() {
        withContext(Dispatchers.IO) {
            try {
                refreshGroups()
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

    private suspend fun refreshGroups() {
        val groups = FootballApi.retrofitService.getGroups().items
        val teams = FootballApi.retrofitService.getTeams().items
        val matches = FootballApi.retrofitService.getMatches().items.filter { currentMatch ->
            currentMatch.groupKey in groups.map {
                it.key
            }
        }
        val goals = FootballApi.retrofitService.getGoals().items.filter { currentGoal ->
            currentGoal.matchKey in matches.map {
                it.key
            }
        }
        val listTeamsWithGroupResults = mutableListOf<TeamWithGroupResult>()
        teams.forEach() { currentTeam ->
            val teamWithResults = TeamWithGroupResult(
                key = currentTeam.key,
                flag = currentTeam.flag,
                name = currentTeam.name,
                groupKey = currentTeam.groupKey,
            )
            fillResult(teamWithResults, matches.filter {
                it.teamOneKey == currentTeam.key || it.teamTwoKey == currentTeam.key
            }, goals)
            listTeamsWithGroupResults.add(teamWithResults)
        }
        database.sportsOracleDao.insertGroups(groups)
        database.sportsOracleDao.insertTeamsWithGroupResults(listTeamsWithGroupResults)
    }

    private fun fillResult(
        teamWithResults: TeamWithGroupResult,
        matches: List<Match>, goals: List<Goal>
    ) {
        var totalGoalFor = 0
        var totalGoalAgainst = 0
        var goalFor = 0
        var goalAgainst = 0
        var won = 0
        var lost = 0
        var drawn = 0
        matches.forEach() { currentMatch ->
            goalFor = 0
            goalAgainst = 0
            goals.filter { it.matchKey == currentMatch.key }.forEach() { currentGoal ->
                if ((currentGoal.teamKey == teamWithResults.key && !currentGoal.ownGoal) || (currentGoal.teamKey != teamWithResults.key && currentGoal.ownGoal))
                    goalFor++
                else
                    goalAgainst++
            }
            totalGoalFor += goalFor
            totalGoalAgainst += goalAgainst
            when (goalFor - goalAgainst) {
                0 -> drawn++
                in 0..Int.MAX_VALUE -> won++
                else -> lost++
            }
        }
        teamWithResults.goalFor = totalGoalFor
        teamWithResults.goalAgainst = totalGoalAgainst
        teamWithResults.won = won
        teamWithResults.lost = lost
        teamWithResults.drawn = drawn
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