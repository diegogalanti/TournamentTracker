package com.gallardo.sportsoracle.data.database.di

import android.content.Context
import androidx.room.Room
import com.gallardo.sportsoracle.data.database.SportsOracleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): SportsOracleDatabase {
        return Room.databaseBuilder(
            context,
            SportsOracleDatabase::class.java,
            SportsOracleDatabase.DATABASE_NAME
        )
            .build()
    }
}