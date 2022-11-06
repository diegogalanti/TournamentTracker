package com.gallardo.sportsoracle.model

data class TeamWithGroupResult (
    val key : String,
    val name : String,
    val flag : String,
    val groupKey : String,
    var won : Int = 0,
    var drawn : Int = 0,
    var lost : Int = 0,
    var goalFor : Int = 0,
    var goalAgainst : Int = 0,
){
    var points = 0
        get() = won * 3 + drawn
    var played = 0
        get() = won + lost + drawn
    var goalDiff = "0"
        get() = "$goalFor - $goalAgainst = ${goalFor - goalAgainst}"
}