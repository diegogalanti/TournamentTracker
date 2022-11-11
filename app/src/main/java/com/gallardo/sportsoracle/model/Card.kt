package com.gallardo.sportsoracle.model

import androidx.room.*
import com.squareup.moshi.Json

@Entity
data class Card(
    @PrimaryKey
    val key: String,

    @Json(name = "match_id")
    @ColumnInfo(name = "match_key")
    val matchKey: String,

    @Json(name = "squad_key")
    @ColumnInfo(name = "squad_key")
    val squadKey: String,

    val type: String
)
