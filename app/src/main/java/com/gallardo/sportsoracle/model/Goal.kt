package com.gallardo.sportsoracle.model

import androidx.room.*
import com.gallardo.sportsoracle.data.network.StringToBoolean
import com.squareup.moshi.Json

@Entity
data class Goal(
    @PrimaryKey
    @Json(name = "key")
    val goalKey: String,

    @Json(name = "match_id")
    @ColumnInfo(name = "match_key")
    val matchKey: String,

    val moment: String,

    @StringToBoolean
    @Json(name = "own_goal")
    @ColumnInfo(name = "own_goal")
    val ownGoal: Boolean,

    @Json(name = "squad_key")
    @ColumnInfo(name = "squad_key")
    val squadKey: String,

    @Json(name = "team_id")
    @ColumnInfo(name = "team_key")
    val teamKey: String
)
