package com.gallardo.sportsoracle.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase
import com.gallardo.sportsoracle.data.network.FootballApi
import com.gallardo.sportsoracle.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MatchesRepository {
    fun getMatchesDates(): LiveData<List<String>>

    fun getMatchesWithTeamsDetails(date: String): LiveData<List<MatchWithTeamsDetails>>

    suspend fun refreshMatchesDatabase()
}