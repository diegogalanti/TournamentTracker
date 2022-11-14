package com.gallardo.sportsoracle.data

import androidx.lifecycle.LiveData
import com.gallardo.sportsoracle.data.database.model.MatchWithTeamsDetailsEntity

interface MatchesRepository {
    fun getMatchesDates(): LiveData<List<String>>

    fun getMatchesWithTeamsDetails(date: String): LiveData<List<MatchWithTeamsDetailsEntity>>

    suspend fun refreshMatchesDatabase()
}