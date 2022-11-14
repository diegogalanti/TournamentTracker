package com.gallardo.sportsoracle.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.gallardo.sportsoracle.data.database.GroupsDao
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase
import com.gallardo.sportsoracle.data.network.FootballApi
import com.gallardo.sportsoracle.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GroupsRepositoryImpl @Inject constructor(private val groupsDao: GroupsDao) : GroupsRepository {
    override fun getGroups(): LiveData<Map<Group, List<TeamWithGroupResult>>> {
        return groupsDao.getGroups()
    }

    override fun getTeamsWithGroupResults(): LiveData<List<TeamWithGroupResult>> {
        return groupsDao.getTeamsWithGroupResults()
    }

    override suspend fun refreshGroupsDatabase() {
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
        groupsDao.insertGroups(groups)
        groupsDao.insertTeamsWithGroupResults(listTeamsWithGroupResults)
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
}