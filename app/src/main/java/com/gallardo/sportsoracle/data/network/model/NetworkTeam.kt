package com.gallardo.sportsoracle.data.network.model

import androidx.room.*
import com.squareup.moshi.Json

data class NetworkTeam(
    val key: String,

    val flag: String,

    @Json(name = "group_key")
    val groupKey: String,

    val name: String
)
