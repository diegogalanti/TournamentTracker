package com.gallardo.sportsoracle.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gallardo.sportsoracle.data.database.MatchesDao
import com.gallardo.sportsoracle.data.database.model.MatchWithTeamsDetailsEntity
import com.gallardo.sportsoracle.data.database.model.asExternal
import com.gallardo.sportsoracle.data.network.FootballApi
import com.gallardo.sportsoracle.data.network.model.asEntity
import com.gallardo.sportsoracle.model.MatchWithTeamsDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(private val matchesDao: MatchesDao) : MatchesRepository {

    override fun getMatchesDates(): Flow<List<String>> {
        return matchesDao.getMatchesDates()
    }

    override fun getMatchesWithTeamsDetails(date: String): Flow<List<MatchWithTeamsDetails>> {
        return Transformations.map(matchesDao.getMatchesWithTeamsDetails(date)) {
            it.map { currentEntry ->  currentEntry.asExternal() }
        }
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
        matchesDao.insertMatches(matches.map { it.asEntity() })
        matchesDao.insertMatchesWithTeamsDetails(listMatchesWithTeamsDetails)
    }
}