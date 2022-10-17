package com.gallardo.sportsoracle.network

import com.gallardo.sportsoracle.model.Group
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    val items: List<Group>
)



