package com.gallardo.sportsoracle.view

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView

import com.gallardo.sportsoracle.databinding.FragmentGroupsBinding
import com.gallardo.sportsoracle.view.rvadapter.GroupsListAdapter
import com.gallardo.sportsoracle.viewmodels.GroupsViewModel
import kotlinx.coroutines.*


class GroupsFragment : Fragment() {

    private val groupsViewModel: GroupsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGroupsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.groupsViewModel = groupsViewModel
        binding.groupList.adapter = GroupsListAdapter()
        binding.groupList.addItemDecoration(MarginItemDecoration(32))
        binding.swiperefresh.setOnRefreshListener {
            CoroutineScope(Dispatchers.IO).launch {
                groupsViewModel.refreshGroups()
                binding.swiperefresh.isRefreshing = false
            }
        }

        return binding.root
    }
}

class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSize
            }
            left = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}
