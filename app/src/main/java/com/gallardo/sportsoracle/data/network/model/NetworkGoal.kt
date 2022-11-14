package com.gallardo.sportsoracle.data.network.model

import androidx.room.*
import com.gallardo.sportsoracle.data.network.StringToBoolean
import com.squareup.moshi.Json

data class NetworkGoal(
    @Json(name = "key")
    val goalKey: String,

    @Json(name = "match_id")
    val matchKey: String,

    val moment: String,

    @StringToBoolean
    @Json(name = "own_goal")
    val ownGoal: Boolean,

    @Json(name = "squad_key")
    val squadKey: String,

    @Json(name = "team_id")
    val teamKey: String
)
