package com.gallardo.sportsoracle.data

import androidx.lifecycle.LiveData
import com.gallardo.sportsoracle.data.database.model.TeamWithGroupResultEntity
import com.gallardo.sportsoracle.model.Group
import com.gallardo.sportsoracle.model.TeamWithGroupResult
import kotlinx.coroutines.flow.Flow

interface GroupsRepository {
    fun getGroups(): Flow<Map<Group, List<TeamWithGroupResult>>>

    fun getTeamsWithGroupResults(): Flow<List<TeamWithGroupResult>>

    suspend fun refreshGroupsDatabase()
}