package com.gallardo.sportsoracle.model

import androidx.room.*
import com.squareup.moshi.Json

@Entity(
    //indices = [Index("match_key"),Index("squad_key")],
    foreignKeys = [
        ForeignKey(
            entity = Match::class,
            parentColumns = arrayOf("key"),
            childColumns = arrayOf("match_key"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Squad::class,
            parentColumns = arrayOf("key"),
            childColumns = arrayOf("squad_key"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )]
)
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
