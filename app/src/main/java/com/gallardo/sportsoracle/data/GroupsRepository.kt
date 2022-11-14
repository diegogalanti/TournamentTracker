package com.gallardo.sportsoracle.data

import androidx.lifecycle.LiveData
import com.gallardo.sportsoracle.data.database.model.GroupEntity
import com.gallardo.sportsoracle.data.database.model.TeamWithGroupResultEntity
import com.gallardo.sportsoracle.data.network.model.NetworkGroup

interface GroupsRepository {
    fun getGroups(): LiveData<Map<GroupEntity, List<TeamWithGroupResultEntity>>>

    fun getTeamsWithGroupResults(): LiveData<List<TeamWithGroupResultEntity>>

    suspend fun refreshGroupsDatabase()
}