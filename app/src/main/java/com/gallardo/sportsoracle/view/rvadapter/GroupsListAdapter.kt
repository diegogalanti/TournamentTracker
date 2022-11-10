package com.gallardo.sportsoracle.view.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.sportsoracle.databinding.GroupTableBinding
import com.gallardo.sportsoracle.model.Group
import com.gallardo.sportsoracle.model.TeamWithGroupResult
import com.gallardo.sportsoracle.viewmodels.GroupsViewModel


class GroupsListAdapter : ListAdapter<Pair<Group, List<TeamWithGroupResult>>, GroupsListAdapter.GroupViewHolder>(DiffCallback) {

    class GroupViewHolder(private val binding : GroupTableBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (group: Pair<Group,List<TeamWithGroupResult>>) {
            binding.group = group.first
            binding.teams = group.second.sortedByDescending { it.points }
        }
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = getItem(position)
        holder.bind(group)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val binding = GroupTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupViewHolder(binding)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Pair<Group, List<TeamWithGroupResult>>>() {
        override fun areContentsTheSame(oldItem: Pair<Group, List<TeamWithGroupResult>>, newItem: Pair<Group, List<TeamWithGroupResult>>): Boolean {
            return oldItem.first.key == newItem.first.key
        }

        override fun areItemsTheSame(oldItem: Pair<Group, List<TeamWithGroupResult>>, newItem: Pair<Group, List<TeamWithGroupResult>>): Boolean {
            return oldItem == newItem
        }
    }
}