package com.gallardo.sportsoracle.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("key"),
            childColumns = arrayOf("team_key"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Squad(
    @PrimaryKey
    val key: String,

    val captain: String,

    val name: String,

    val position: String,

    @Json (name = "team_key")
    @ColumnInfo(name = "team_key")
    val teamKey: String
)
