package com.gallardo.sportsoracle.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gallardo.sportsoracle.model.Group

@Entity
data class GroupEntity(
    @PrimaryKey
    val gkey: String,
    val gname: String
)

fun GroupEntity.asExternal() = Group(gkey, gname)
