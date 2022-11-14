package com.gallardo.sportsoracle.data.database.di

import com.gallardo.sportsoracle.data.database.GroupsDao
import com.gallardo.sportsoracle.data.database.MatchesDao
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesMatchesDao(database : SportsOracleDatabase) : MatchesDao = database.matchesDao

    @Provides
    fun providesGroupsDao(database : SportsOracleDatabase) : GroupsDao = database.groupsDao
}