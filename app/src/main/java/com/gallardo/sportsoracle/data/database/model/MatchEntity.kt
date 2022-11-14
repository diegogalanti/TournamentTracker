package com.gallardo.sportsoracle.data.database.model

import androidx.room.*
import com.gallardo.sportsoracle.data.database.DateConverter
import com.gallardo.sportsoracle.data.network.StringToDate
import com.squareup.moshi.Json
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
data class MatchEntity(
    @PrimaryKey
    val key: String,

    val date: Date,

    @ColumnInfo(name = "group_key")
    val groupKey: String,

    @ColumnInfo(name = "match_number")
    val matchNumber: Int,

    @ColumnInfo(name = "stadium_key")
    val stadiumKey: String,

    @ColumnInfo(name = "team_two_key")
    val teamTwoKey: String,

    @ColumnInfo(name = "team_one_key")
    val teamOneKey: String,

    val time: Date
)
