package com.gallardo.sportsoracle.data.network.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class NetworkGroup(
    @ColumnInfo(name = "gkey")
    val key: String,
    @ColumnInfo(name = "gname")
    val name: String
)
