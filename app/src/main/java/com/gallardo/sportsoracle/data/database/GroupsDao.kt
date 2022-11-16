package com.gallardo.sportsoracle.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gallardo.sportsoracle.data.database.model.GroupEntity
import com.gallardo.sportsoracle.data.database.model.MatchEntity
import com.gallardo.sportsoracle.data.database.model.MatchWithTeamsDetailsEntity
import com.gallardo.sportsoracle.data.database.model.TeamWithGroupResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupsDao {
    @Query("SELECT * FROM GroupEntity " +
            "LEFT JOIN TeamWithGroupResultEntity ON GroupEntity.`gkey` = TeamWithGroupResultEntity.groupKey"
    )
    fun getGroups(): Flow<Map<GroupEntity, List<TeamWithGroupResultEntity>>>

    @Query("SELECT DISTINCT date FROM MatchEntity " +
            "ORDER BY date")
    fun getMatchesDates() : Flow<List<String>>

    @Query("SELECT * FROM TeamWithGroupResultEntity")
    fun getTeamsWithGroupResults() : Flow<List<TeamWithGroupResultEntity>>

    @Query(
        "SELECT * FROM MatchWithTeamsDetailsEntity " +
                "WHERE MatchWithTeamsDetailsEntity.date = :date"
    )
    fun getMatchesWithTeamsDetails(date: String) : Flow<List<MatchWithTeamsDetailsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroups(groups: List<GroupEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamsWithGroupResults(listTeamsWithGroupResults: List<TeamWithGroupResultEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatches(matches: List<MatchEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchesWithTeamsDetails(listMatchesWithTeamsDetails: List<MatchWithTeamsDetailsEntity>)
}