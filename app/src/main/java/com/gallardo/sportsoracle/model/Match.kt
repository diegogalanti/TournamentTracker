package com.gallardo.sportsoracle.model

import com.squareup.moshi.Json

data class Match(
    val key: String,
    val date: String,
    @Json(name = "group_key")
    val groupKey: String,
    @Json(name = "match_number")
    val matchNumber: String,
    @Json(name = "stadium_key")
    val stadiumKey: String,
    @Json(name = "team_2_key")
    val team2Key: String,
    @Json(name = "team_one_key")
    val teamOneKey: String,
    val time: String
)
