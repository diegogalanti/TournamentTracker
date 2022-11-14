package com.gallardo.sportsoracle.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.gallardo.sportsoracle.data.database.MatchesDao
import com.gallardo.sportsoracle.data.database.model.MatchWithTeamsDetailsEntity
import com.gallardo.sportsoracle.data.network.FootballApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(private val matchesDao: MatchesDao) : MatchesRepository {

    override fun getMatchesDates(): LiveData<List<String>> {
        return matchesDao.getMatchesDates()
    }

    override fun getMatchesWithTeamsDetails(date: String): LiveData<List<MatchWithTeamsDetailsEntity>> {
        return matchesDao.getMatchesWithTeamsDetails(date)
    }

   override suspend fun refreshMatchesDatabase() {
        withContext(Dispatchers.IO) {
            try {
                refreshMatches()
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

    private suspend fun refreshMatches() {
        val matches = FootballApi.retrofitService.getMatches().items
        val teams = FootballApi.retrofitService.getTeams().items
        val listMatchesWithTeamsDetails = mutableListOf<MatchWithTeamsDetailsEntity>()
        matches.forEach() { currentMatch ->
            if (currentMatch.teamOneKey != "-1")
                listMatchesWithTeamsDetails.add(
                    MatchWithTeamsDetailsEntity(
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
                )
                )
        }
        matchesDao.insertMatches(matches)
        matchesDao.insertMatchesWithTeamsDetails(listMatchesWithTeamsDetails)
    }
}