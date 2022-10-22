package com.gallardo.sportsoracle.model

import androidx.room.*
import com.gallardo.sportsoracle.data.network.StringToBoolean
import com.squareup.moshi.Json

@Entity(
    //indices = [Index("match_key"), Index("squad_key"), Index("team_key")],
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
        ),
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("key"),
            childColumns = arrayOf("team_key"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )]
)
data class Goal(
    @PrimaryKey
    val key: String,

    @Json(name = "match_id")
    @ColumnInfo(name = "match_key")
    val matchKey: String,

    val moment: String,

    @StringToBoolean
    @Json(name = "own_goal")
    @ColumnInfo(name = "own_goal")
    val ownGoal: Boolean,

    @Json(name = "squad_key")
    @ColumnInfo(name = "squad_key")
    val squadKey: String,

    @Json(name = "team_id")
    @ColumnInfo(name = "team_key")
    val teamKey: String
)
