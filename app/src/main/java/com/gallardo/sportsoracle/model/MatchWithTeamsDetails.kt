package com.gallardo.sportsoracle.model

import java.util.*

data class MatchWithTeamsDetails(
    val key: String,
    val date: Date,
    val groupKey: String,
    val stadiumKey: String,
    val time: Date,
    val teamOneName: String,
    val teamOneFlag: String,
    val teamTwoName: String,
    val teamTwoFlag: String,
)
