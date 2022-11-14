package com.gallardo.sportsoracle.data.database

import androidx.room.*
import com.gallardo.sportsoracle.data.database.model.MatchWithTeamsDetailsEntity
import com.gallardo.sportsoracle.data.database.model.TeamWithGroupResultEntity
import com.gallardo.sportsoracle.data.network.model.*

@Database(
    entities = [NetworkCard::class, NetworkGoal::class, NetworkGroup::class, NetworkMatch::class, NetworkSquad::class, NetworkStadium::class, NetworkTeam::class, TeamWithGroupResultEntity::class, MatchWithTeamsDetailsEntity::class],
    version = 1
)
abstract class SportsOracleDatabase : RoomDatabase() {

    abstract val matchesDao: MatchesDao

    abstract val groupsDao: GroupsDao
}