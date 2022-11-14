package com.gallardo.sportsoracle.data.network.model

import androidx.room.*
import com.gallardo.sportsoracle.data.database.DateConverter
import com.gallardo.sportsoracle.data.network.StringToDate
import com.squareup.moshi.Json
import java.util.*

data class NetworkMatch(
    val key: String,

    @StringToDate
    val date: Date,

    @Json(name = "group_key")
    val groupKey: String,

    @Json(name = "match_number")
    val matchNumber: Int,

    @Json(name = "stadium_key")
    val stadiumKey: String,

    @Json(name = "team_2_key")
    val teamTwoKey: String,

    @Json(name = "team_one_key")
    val teamOneKey: String,

    @StringToDate
    val time: Date
)
