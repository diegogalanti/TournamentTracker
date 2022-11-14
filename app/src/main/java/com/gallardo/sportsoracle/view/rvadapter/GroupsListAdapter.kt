package com.gallardo.sportsoracle.view.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.sportsoracle.data.database.model.GroupEntity
import com.gallardo.sportsoracle.databinding.GroupTableBinding
import com.gallardo.sportsoracle.data.network.model.NetworkGroup
import com.gallardo.sportsoracle.data.database.model.TeamWithGroupResultEntity


class GroupsListAdapter : ListAdapter<Pair<GroupEntity, List<TeamWithGroupResultEntity>>, GroupsListAdapter.GroupViewHolder>(DiffCallback) {

    class GroupViewHolder(private val binding : GroupTableBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (group: Pair<GroupEntity,List<TeamWithGroupResultEntity>>) {
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

    companion object DiffCallback : DiffUtil.ItemCallback<Pair<GroupEntity, List<TeamWithGroupResultEntity>>>() {
        override fun areContentsTheSame(oldItem: Pair<GroupEntity, List<TeamWithGroupResultEntity>>, newItem: Pair<GroupEntity, List<TeamWithGroupResultEntity>>): Boolean {
            return oldItem.first.gkey == newItem.first.gkey
        }

        override fun areItemsTheSame(oldItem: Pair<GroupEntity, List<TeamWithGroupResultEntity>>, newItem: Pair<GroupEntity, List<TeamWithGroupResultEntity>>): Boolean {
            return oldItem == newItem
        }
    }
}