package com.gallardo.sportsoracle.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gallardo.sportsoracle.data.database.DateConverter
import com.gallardo.sportsoracle.data.network.StringToDate
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
data class MatchWithTeamsDetailsEntity(
    @PrimaryKey
    val key: String,
    @StringToDate
    val date: Date,
    @ColumnInfo(name = "group_key")
    val groupKey: String,
    @ColumnInfo(name = "stadium_key")
    val stadiumKey: String,
    val time: Date,
    @ColumnInfo(name = "team_one_name")
    val teamOneName: String,
    @ColumnInfo(name = "team_one_flag")
    val teamOneFlag: String,
    @ColumnInfo(name = "team_two_key")
    val teamTwoName: String,
    @ColumnInfo(name = "team_two_flag")
    val teamTwoFlag: String,
)
