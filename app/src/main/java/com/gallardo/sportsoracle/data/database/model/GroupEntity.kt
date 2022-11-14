package com.gallardo.sportsoracle.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupEntity(
    @PrimaryKey
    val gkey: String,
    val gname: String
)
