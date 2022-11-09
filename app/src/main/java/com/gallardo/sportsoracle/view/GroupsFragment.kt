package com.gallardo.sportsoracle.view

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import com.gallardo.sportsoracle.R
import com.gallardo.sportsoracle.databinding.FragmentGroupsBinding
import com.gallardo.sportsoracle.view.rvadapter.GroupsListAdapter
import com.gallardo.sportsoracle.viewmodels.GroupsViewModel


class GroupsFragment : Fragment() {

    private val groupsViewModel: GroupsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGroupsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.groupsViewModel = groupsViewModel
        binding.groupList.adapter = GroupsListAdapter(groupsViewModel)
        binding.groupList.addItemDecoration(MarginItemDecoration(32))

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
