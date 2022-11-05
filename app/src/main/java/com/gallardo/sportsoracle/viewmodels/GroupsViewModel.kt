package com.gallardo.sportsoracle.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gallardo.sportsoracle.data.SportsOracleRepository
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase.Companion.getDatabase
import com.gallardo.sportsoracle.model.*
import kotlinx.coroutines.runBlocking
import java.util.*


class GroupsViewModel(application: Application) : AndroidViewModel(application) {

    private val sportsOracleRepository = SportsOracleRepository(getDatabase(application))

    init {
        refreshDataFromRepository()
    }

    val groups: List<Group> = sportsOracleRepository.getGroups()

    fun getGroupTeamsFlags(groupKey: String): List<String> {
        return sportsOracleRepository.getGroupTeamsFlags(groupKey)
    }

    fun getTeamsWithResults(groupKey: String): List<TeamWithGroupResult> {
        val groupTeams = sportsOracleRepository.getGroupTeams(groupKey)
        val groupMatchesAndGoals = sportsOracleRepository.getGroupMatchesAndGoals(groupKey)
        val listTeamsWithResults = mutableListOf<TeamWithGroupResult>()
        groupTeams.forEach() { currentTeam ->
            val teamWithResults = TeamWithGroupResult(
                key = currentTeam.key,
                flag = currentTeam.flag,
                name = currentTeam.name,
                groupKey = currentTeam.groupKey,
            )
            fillResult(teamWithResults, groupMatchesAndGoals)
            listTeamsWithResults.add(teamWithResults)
        }
        listTeamsWithResults.sortByDescending { it.points }
        return listTeamsWithResults
    }

    private fun fillResult(
        teamWithResults: TeamWithGroupResult,
        groupMatchesAndGoals: Map<Match, List<Goal>>
    ) {
        var totalGoalFor = 0
        var totalGoalAgainst = 0
        var goalFor = 0
        var goalAgainst = 0
        var won = 0
        var lost = 0
        var drawn = 0
        groupMatchesAndGoals.forEach() { currentMatch ->
            if (currentMatch.key.teamOneKey == teamWithResults.key || currentMatch.key.teamTwoKey == teamWithResults.key)
            {
                goalFor = 0
                goalAgainst = 0
                currentMatch.value.forEach() { currentGoal ->
                    if((currentGoal.teamKey == teamWithResults.key && !currentGoal.ownGoal) || (currentGoal.teamKey != teamWithResults.key && currentGoal.ownGoal))
                        goalFor++
                    else
                        goalAgainst++
                }
                totalGoalFor += goalFor
                totalGoalAgainst += goalAgainst
                when (goalFor - goalAgainst) {
                    0 -> drawn++
                    in 0 .. Int.MAX_VALUE -> won++
                    else -> lost++
                }
            }
        }
        teamWithResults.goalFor = totalGoalFor
        teamWithResults.goalAgainst = totalGoalAgainst
        teamWithResults.won = won
        teamWithResults.lost = lost
        teamWithResults.drawn = drawn
    }

    fun currentDate() : Date {
        return return Calendar.getInstance().time
    }

    private fun refreshDataFromRepository() {
        runBlocking {
            sportsOracleRepository.refreshDatabase()
        }
    }
}