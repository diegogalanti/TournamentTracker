package com.gallardo.sportsoracle.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallardo.sportsoracle.network.FootballApi
import com.gallardo.sportsoracle.network.Group
import kotlinx.coroutines.launch

class GroupsViewModel : ViewModel() {

    val groups = MutableLiveData<List<Group>>()

    init {
        getLeagues()
    }

    private fun getLeagues() {
        viewModelScope.launch {
            groups.value = FootballApi.retrofitService.getGroups().items
            Log.e("Foi",groups.value.toString())
        }
    }
}