package com.gallardo.sportsoracle.model

import com.squareup.moshi.Json

data class Squad(
    val key: String,
    val captain: String,
    val name: String,
    val position: String,
    @Json (name = "team_key")
    val teamKey: String
)
