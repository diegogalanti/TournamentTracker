package com.gallardo.sportsoracle.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey
    val key: String,
    val name: String
)
