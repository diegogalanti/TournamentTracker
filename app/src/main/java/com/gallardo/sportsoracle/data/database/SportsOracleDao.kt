package com.gallardo.sportsoracle.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gallardo.sportsoracle.model.*
import java.util.*

@Dao
interface SportsOracleDao {
    @Query("SELECT * FROM `Group`")
    fun getGroups(): List<Group>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroups(groups: List<Group>)

    @Query(
        "SELECT flag FROM Team " +
                "WHERE group_key = :key"
    )
    fun getGroupTeamsFlags(key: String): List<String>

    @Query(
        "SELECT * FROM Team " +
                "WHERE group_key = :key"
    )
    fun getGroupTeams(key: String): List<Team>

    @Query("SELECT flag FROM Team")
    fun getTeamsFlags(): List<String>

    @Query("SELECT * FROM `Match` " +
                "WHERE date = :date")
    fun getMatches(date: String): List<Match>

    @Query("SELECT * FROM Team")
    fun getTeams(): List<Team>

    @Query("SELECT * FROM `match` " +
            "LEFT JOIN goal ON `match`.`key` = goal.match_key " +
            "WHERE group_key = :groupKey"
    )
    fun getGroupMatchesAndGoals(groupKey: String): Map<Match, List<Goal>>

    @Query("SELECT DISTINCT date FROM `match`")
    fun getMatchesDates() : List<String>

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
}