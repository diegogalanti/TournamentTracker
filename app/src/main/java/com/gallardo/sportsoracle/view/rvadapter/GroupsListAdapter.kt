package com.gallardo.sportsoracle.view.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.sportsoracle.R
import com.gallardo.sportsoracle.databinding.GroupItemBinding
import com.gallardo.sportsoracle.databinding.GroupTableBinding
import com.gallardo.sportsoracle.model.Group

class GroupsListAdapter : ListAdapter<Group, GroupsListAdapter.GroupViewHolder>(DiffCallback) {


    class GroupViewHolder(private val binding : GroupTableBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (group: Group) {
            binding.group = group
        }
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = getItem(position)
        holder.bind(group)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(GroupTableBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Group>() {
        override fun areContentsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem.key == newItem.key
        }
    }
}