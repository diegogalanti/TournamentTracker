package com.gallardo.sportsoracle.data.network.model

import com.squareup.moshi.Json

data class NetworkCard(
    val key: String,
    @Json(name = "match_id")
    val matchKey: String,

    @Json(name = "squad_key")
    val squadKey: String,

    val type: String
)
