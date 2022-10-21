package com.gallardo.sportsoracle.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Group::class,
            parentColumns = arrayOf("key"),
            childColumns = arrayOf("group_key"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Team(
    @PrimaryKey
    val key: String,

    val flag: String,

    @Json(name = "group_key")
    @ColumnInfo(name = "group_key")
    val groupKey: String,

    val name: String
)
