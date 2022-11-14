package com.gallardo.sportsoracle.data.network

import com.gallardo.sportsoracle.data.network.model.*
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupResponse(
    val items: List<NetworkGroup>
)

@JsonClass(generateAdapter = true)
data class CardResponse(
    val items: List<NetworkCard>
)

@JsonClass(generateAdapter = true)
data class GoalResponse(
    val items: List<NetworkGoal>
)

@JsonClass(generateAdapter = true)
data class MatchResponse(
    val items: List<NetworkMatch>
)

@JsonClass(generateAdapter = true)
data class TeamResponse(
    val items: List<NetworkTeam>
)

@JsonClass(generateAdapter = true)
data class SquadResponse(
    val items: List<NetworkSquad>
)

@JsonClass(generateAdapter = true)
data class StadiumResponse(
    val items: List<NetworkStadium>
)



