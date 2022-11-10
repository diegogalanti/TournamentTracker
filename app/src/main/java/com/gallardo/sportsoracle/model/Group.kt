package com.gallardo.sportsoracle.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey
    @ColumnInfo(name = "gkey")
    val key: String,
    @ColumnInfo(name = "gname")
    val name: String
)
