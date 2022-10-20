package com.gallardo.sportsoracle.network

import com.gallardo.sportsoracle.model.*
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupResponse(
    val items: List<Group>
)

@JsonClass(generateAdapter = true)
data class CardResponse(
    val items: List<Card>
)

@JsonClass(generateAdapter = true)
data class GoalResponse(
    val items: List<Goal>
)

@JsonClass(generateAdapter = true)
data class MatchResponse(
    val items: List<Match>
)

@JsonClass(generateAdapter = true)
data class TeamResponse(
    val items: List<Team>
)

@JsonClass(generateAdapter = true)
data class SquadResponse(
    val items: List<Squad>
)

@JsonClass(generateAdapter = true)
data class StadiumResponse(
    val items: List<Stadium>
)



