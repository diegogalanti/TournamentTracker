package com.gallardo.sportsoracle.viewmodels

import android.R.attr.bitmap
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
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
        getGroups()
    }

    private fun getGroups() {
        viewModelScope.launch {
            _groups.value = FootballApi.retrofitService.getGroups().items.sortedBy {
                it.name
            }
            val file = groups.value?.get(2)?.teams?.get(1)?.logo?.let { File(it) }

            Log.e("Foi",groups.value.toString())
        }
    }
}