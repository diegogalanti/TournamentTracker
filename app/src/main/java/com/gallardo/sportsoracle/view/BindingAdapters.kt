package com.gallardo.sportsoracle.view

import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.sportsoracle.databinding.GroupItemBinding
import com.gallardo.sportsoracle.model.Group
import com.gallardo.sportsoracle.model.Team
import com.gallardo.sportsoracle.view.rvadapter.GroupsListAdapter

@BindingAdapter("groups_list")
fun bindGroupsListAdapter(
    recyclerView: RecyclerView,
    data: List<Group>?
) {
    val adapter = recyclerView.adapter as GroupsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("group_teams")
fun bindGroupTeams(
    linearLayout: LinearLayout,
    data: List<Team>?
) {
    if (linearLayout.childCount == 0)
        data?.forEach() {
            val teamView = GroupItemBinding.inflate(LayoutInflater.from(linearLayout.context))
            teamView.team = it
            linearLayout.addView(teamView.root)
        }
}