package com.gallardo.sportsoracle.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Goal(
    @PrimaryKey
    val key: String,

    @Json(name = "match_id")
    @ColumnInfo(name = "match_key")
    val matchKey: String,

    val moment: String,

    @Json(name = "own_goal")
    @ColumnInfo(name = "own_goal")
    val ownGoal: String,

    @Json(name = "squad_key")
    @ColumnInfo(name = "squad_key")
    val squadKey: String,

    @Json(name = "team_id")
    @ColumnInfo(name = "team_key")
    val teamKey: String
)
