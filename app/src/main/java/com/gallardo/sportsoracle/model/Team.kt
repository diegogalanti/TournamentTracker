package com.gallardo.sportsoracle.model

import com.squareup.moshi.Json

data class Team(
    val key: String,
    val flag: String,
    @Json(name = "group_key")
    val groupKey: String,
    val name: String
)
