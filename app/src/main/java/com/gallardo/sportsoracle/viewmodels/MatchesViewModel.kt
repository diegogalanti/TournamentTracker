package com.gallardo.sportsoracle.viewmodels

import androidx.lifecycle.*
import com.gallardo.sportsoracle.data.MatchesRepository
import com.gallardo.sportsoracle.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesRepository : MatchesRepository) : ViewModel() {

    init {
        CoroutineScope(Dispatchers.IO).launch { refreshMatches() }
    }

   val matchDates = matchesRepository.getMatchesDates()

    fun matchesWithTeamDetails(date: String) : LiveData<List<MatchWithTeamsDetails>> {
        return matchesRepository.getMatchesWithTeamsDetails(date)
    }

    private suspend fun refreshMatches() {
        matchesRepository.refreshMatchesDatabase()
    }
}