package com.gallardo.sportsoracle.data.di

import com.gallardo.sportsoracle.data.GroupsRepository
import com.gallardo.sportsoracle.data.GroupsRepositoryImpl
import com.gallardo.sportsoracle.data.MatchesRepository
import com.gallardo.sportsoracle.data.MatchesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsMatchesRepository(
        matchesRepository: MatchesRepositoryImpl
    ): MatchesRepository

    @Binds
    fun bindsGroupsRepository(
        groupsRepository: GroupsRepositoryImpl
    ): GroupsRepository
}