package com.gallardo.sportsoracle.model

import com.squareup.moshi.Json

data class Goal(
    val key: String,
    @Json(name = "match_id")
    val matchKey: String,
    val moment: String,
    @Json(name = "own_goal")
    val ownGoal: String,
    @Json(name = "squad_key")
    val squadKey: String,
    @Json(name = "team_id")
    val teamKey: String
)
