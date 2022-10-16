package com.gallardo.sportsoracle.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    val items: List<Group>
)

data class Group(
    val key: String,
    val name: String,
    val teams : List<Team>
)

data class Team(
    val name: String,
    val logo: String,
)