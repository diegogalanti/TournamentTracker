package com.gallardo.sportsoracle.model

data class Group(
    val key: String,
    val name: String,
    val teams : List<Team>
)
