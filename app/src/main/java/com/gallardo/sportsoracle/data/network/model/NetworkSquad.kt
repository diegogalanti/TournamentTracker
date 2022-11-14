package com.gallardo.sportsoracle.data.network.model

import androidx.room.*
import com.squareup.moshi.Json

data class NetworkSquad(
    val key: String,

    val captain: String,

    val name: String,

    val position: String,

    @Json (name = "team_key")
    @ColumnInfo(name = "team_key")
    val teamKey: String
)
