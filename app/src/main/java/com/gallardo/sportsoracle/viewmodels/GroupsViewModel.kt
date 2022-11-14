package com.gallardo.sportsoracle.viewmodels

import androidx.lifecycle.*
import com.gallardo.sportsoracle.data.GroupsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val groupsRepository : GroupsRepository) : ViewModel() {

    init {
        CoroutineScope(Dispatchers.IO).launch { refreshGroups() }
    }

    val groups = groupsRepository.getGroups()

    suspend fun refreshGroups() {
        groupsRepository.refreshGroupsDatabase()
    }
}