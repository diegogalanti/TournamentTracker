package com.gallardo.sportsoracle.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.gallardo.sportsoracle.databinding.FragmentMatchesBinding
import com.gallardo.sportsoracle.view.rvadapter.MatchesListAdapter
import com.gallardo.sportsoracle.viewmodels.MatchesViewModel


class MatchesFragment : Fragment() {
    private val matchesViewModel: MatchesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMatchesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.matchesViewModel = matchesViewModel
        binding.matchList.adapter = MatchesListAdapter()
        binding.matchList.addItemDecoration(MarginItemDecoration(32))

        return binding.root
    }
}