package com.gallardo.sportsoracle.data

import androidx.lifecycle.LiveData
import com.gallardo.sportsoracle.model.MatchWithTeamsDetails
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    fun getMatchesDates(): Flow<List<String>>

    fun getMatchesWithTeamsDetails(date: String): Flow<List<MatchWithTeamsDetails>>

    suspend fun refreshMatchesDatabase()
}