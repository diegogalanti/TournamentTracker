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
        ),
        ForeignKey(
            entity = Stadium::class,
            parentColumns = arrayOf("key"),
            childColumns = arrayOf("stadium_key"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("key"),
            childColumns = arrayOf("team_one_key"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Team::class,
            parentColumns = arrayOf("key"),
            childColumns = arrayOf("team_two_key"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )]
)
data class Match(
    @PrimaryKey
    val key: String,

    val date: String,

    @ColumnInfo(name = "group_key")
    @Json(name = "group_key")
    val groupKey: String,

    @ColumnInfo(name = "match_number")
    @Json(name = "match_number")
    val matchNumber: String,

    @ColumnInfo(name = "stadium_key")
    @Json(name = "stadium_key")
    val stadiumKey: String,

    @ColumnInfo(name = "team_two_key")
    @Json(name = "team_2_key")
    val team2Key: String,

    @ColumnInfo(name = "team_one_key")
    @Json(name = "team_one_key")
    val teamOneKey: String,

    val time: String
)
