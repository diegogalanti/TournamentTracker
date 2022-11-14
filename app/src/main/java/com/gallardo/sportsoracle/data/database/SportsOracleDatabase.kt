package com.gallardo.sportsoracle.data.database

import androidx.room.*
import com.gallardo.sportsoracle.model.*

@Database(
    entities = [Card::class, Goal::class, Group::class, Match::class, Squad::class, Stadium::class, Team::class, TeamWithGroupResult::class, MatchWithTeamsDetails::class],
    version = 1
)
abstract class SportsOracleDatabase : RoomDatabase() {

    abstract val matchesDao: MatchesDao

    abstract val groupsDao: GroupsDao
}