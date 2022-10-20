package com.gallardo.sportsoracle.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallardo.sportsoracle.model.Group
import com.gallardo.sportsoracle.network.FootballApi
import kotlinx.coroutines.launch
import java.io.File


class GroupsViewModel : ViewModel() {

    private val _groups = MutableLiveData<List<Group>>()

    val groups: LiveData<List<Group>> = _groups

    init {
        getCards()
        getGoals()
        getGroups()
        getMatches()
        getSquads()
        getStadiums()
        getTeams()
    }

    private fun getTeams() {
        viewModelScope.launch {
            Log.e("Groups", FootballApi.retrofitService.getTeams().items.toString())
        }
    }

    private fun getStadiums() {
        viewModelScope.launch {
            Log.e("Stadiums", FootballApi.retrofitService.getStadiums().items.toString())
        }
    }

    private fun getSquads() {
        viewModelScope.launch {
            Log.e("Squads()", FootballApi.retrofitService.getSquads().items.toString())
        }
    }

    private fun getMatches() {
        viewModelScope.launch {
            Log.e("Matches()", FootballApi.retrofitService.getMatches().items.toString())
        }
    }

    private fun getGroups() {
        viewModelScope.launch {
            Log.e("Groups()", FootballApi.retrofitService.getGroups().items.toString())
        }
    }

    private fun getGoals() {
        viewModelScope.launch {
            Log.e("Goals()", FootballApi.retrofitService.getGoals().items.toString())
        }
    }

    private fun getCards() {
        viewModelScope.launch {
            Log.e("Cards()", FootballApi.retrofitService.getCards().items.toString())
        }
    }

//    private fun getGroups() {
//        viewModelScope.launch {
//            _groups.value = FootballApi.retrofitService.getGroups().items.sortedBy {
//                it.name
//            }
//            val file = groups.value?.get(2)?.teams?.get(1)?.logo?.let { File(it) }
//
//            Log.e("Foi",groups.value.toString())
//        }
//    }
}