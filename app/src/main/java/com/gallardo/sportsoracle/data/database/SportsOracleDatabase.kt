package com.gallardo.sportsoracle.data.database

import android.content.Context
import androidx.room.*
import com.gallardo.sportsoracle.model.*

@Database(
    entities = [Card::class, Goal::class, Group::class, Match::class, Squad::class, Stadium::class, Team::class],
    version = 1
)
abstract class SportsOracleDatabase : RoomDatabase() {

    abstract val sportsOracleDao: SportsOracleDao

    companion object {
        @Volatile
        private var INSTANCE: SportsOracleDatabase? = null

        fun getDatabase(context: Context): SportsOracleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    SportsOracleDatabase::class.java,
                    "app_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}