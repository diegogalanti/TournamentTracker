package com.gallardo.sportsoracle.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase
import com.gallardo.sportsoracle.data.network.FootballApi
import com.gallardo.sportsoracle.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GroupsRepository {
    fun getGroups(): LiveData<Map<Group, List<TeamWithGroupResult>>>

    fun getTeamsWithGroupResults(): LiveData<List<TeamWithGroupResult>>

    suspend fun refreshGroupsDatabase()
}