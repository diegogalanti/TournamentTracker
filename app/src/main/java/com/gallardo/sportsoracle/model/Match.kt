package com.gallardo.sportsoracle.model

import androidx.room.*
import com.gallardo.sportsoracle.data.database.DateConverter
import com.gallardo.sportsoracle.data.network.StringToDate
import com.squareup.moshi.Json
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
data class Match(
    @PrimaryKey
    val key: String,

    @StringToDate
    val date: Date,

    @ColumnInfo(name = "group_key")
    @Json(name = "group_key")
    val groupKey: String,

    @ColumnInfo(name = "match_number")
    @Json(name = "match_number")
    val matchNumber: Int,

    @ColumnInfo(name = "stadium_key")
    @Json(name = "stadium_key")
    val stadiumKey: String,

    @ColumnInfo(name = "team_two_key")
    @Json(name = "team_2_key")
    val teamTwoKey: String,

    @ColumnInfo(name = "team_one_key")
    @Json(name = "team_one_key")
    val teamOneKey: String,

    @StringToDate
    val time: Date
)
