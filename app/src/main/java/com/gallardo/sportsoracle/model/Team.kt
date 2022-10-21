package com.gallardo.sportsoracle.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Team(
    @PrimaryKey
    val key: String,

    val flag: String,

    @Json(name = "group_key")
    @ColumnInfo(name = "group_key")
    val groupKey: String,

    val name: String
)
