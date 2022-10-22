package com.gallardo.sportsoracle.model

import androidx.room.*
import com.squareup.moshi.Json

@Entity(
    //indices = [Index("group_key")],
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
