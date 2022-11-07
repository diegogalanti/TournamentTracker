package com.gallardo.sportsoracle.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gallardo.sportsoracle.data.SportsOracleRepository
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase.Companion.getDatabase
import com.gallardo.sportsoracle.model.*
import kotlinx.coroutines.runBlocking
import java.util.*


class MatchesViewModel(application: Application) : AndroidViewModel(application) {

    private val sportsOracleRepository = SportsOracleRepository(getDatabase(application))

    fun getMatchesWithTeamsDetails(): List<MatchWithTeamsDetails> {
        val matches = sportsOracleRepository.getMatches()
        val teams = sportsOracleRepository.getTeams()
        Log.e("Teams", teams.toString())
        val listMatches = mutableListOf<MatchWithTeamsDetails>()
        matches.forEach() { currentMatch ->
            if (currentMatch.teamOneKey != "-1")
                listMatches.add(MatchWithTeamsDetails(
                    currentMatch.key,
                    currentMatch.date,
                    currentMatch.groupKey,
                    currentMatch.stadiumKey,
                    currentMatch.time,
                    teams.first {
                        it.key == currentMatch.teamOneKey
                    }.name,
                    teams.first {
                        it.key == currentMatch.teamOneKey
                    }.flag,
                    teams.first {
                        it.key == currentMatch.teamTwoKey
                    }.name,
                    teams.first {
                        it.key == currentMatch.teamTwoKey
                    }.flag
                ))
        }
        Log.e("Current", listMatches.toString())
        return listMatches
    }

    val matchDates = sportsOracleRepository.getMatchesDates()

    private fun refreshDataFromRepository() {
        runBlocking {
            sportsOracleRepository.refreshDatabase()
        }
    }
}