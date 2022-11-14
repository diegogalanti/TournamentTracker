package com.gallardo.sportsoracle.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gallardo.sportsoracle.model.*
import java.util.*

@Dao
interface MatchesDao {
    @Query("SELECT * FROM `Group` " +
            "LEFT JOIN TeamWithGroupResult ON `Group`.`gkey` = TeamWithGroupResult.groupKey")
    fun getGroups(): LiveData<Map<Group, List<TeamWithGroupResult>>>

    @Query("SELECT DISTINCT date FROM `match` " +
            "ORDER BY date")
    fun getMatchesDates() : LiveData<List<String>>

    @Query("SELECT * FROM TeamWithGroupResult")
    fun getTeamsWithGroupResults() : LiveData<List<TeamWithGroupResult>>

    @Query("SELECT * FROM MatchWithTeamsDetails " +
            "WHERE MatchWithTeamsDetails.date = :date")
    fun getMatchesWithTeamsDetails(date: String) : LiveData<List<MatchWithTeamsDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroups(groups: List<Group>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeamsWithGroupResults(listTeamsWithGroupResults: List<TeamWithGroupResult>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatches(matches: List<Match>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchesWithTeamsDetails(listMatchesWithTeamsDetails: List<MatchWithTeamsDetails>)
}