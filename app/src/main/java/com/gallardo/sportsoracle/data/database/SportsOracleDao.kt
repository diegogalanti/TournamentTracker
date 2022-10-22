package com.gallardo.sportsoracle.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gallardo.sportsoracle.model.*

@Dao
interface SportsOracleDao {
    @Query("SELECT * FROM `Group`")
    fun getGroups(): List<Group>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroups(groups: List<Group>)

    @Query(
        "SELECT * FROM Team " +
                "WHERE group_key = :key"
    )
    fun getGroupTeams(key: String): List<Team>

    @Query("SELECT flag FROM Team")
    fun getTeamsFlags(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCards(cards: List<Card>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoals(goals: List<Goal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatches(matches: List<Match>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSquads(squads: List<Squad>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: List<Team>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStadiums(stadiums: List<Stadium>)

//    @Query("SELECT * FROM 'match'" +
//    "WHERE team_one_key = :teamKey")
//    fun getTeamFinishedMatches(teamKey: String): List<Card>
}