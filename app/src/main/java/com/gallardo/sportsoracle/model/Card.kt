package com.gallardo.sportsoracle.model

import com.squareup.moshi.Json

data class Card(
    val key: String,
    @Json(name = "match_id")
    val matchKey: String,
    @Json(name = "squad_key")
    val squadKey: String,
    val type: String
)
