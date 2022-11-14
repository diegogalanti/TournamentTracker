package com.gallardo.sportsoracle.view.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.sportsoracle.databinding.MatchTableBinding
import com.gallardo.sportsoracle.data.database.model.MatchWithTeamsDetailsEntity

class MatchesListAdapter : ListAdapter<MatchWithTeamsDetailsEntity, MatchesListAdapter.MatchViewHolder>(DiffCallback) {

    class MatchViewHolder(private val binding: MatchTableBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (match: MatchWithTeamsDetailsEntity) {
            binding.timeVar = match.time.toString()
            binding.teamOneNameVar = match.teamOneName
            binding.teamOneFlagVar = match.teamOneFlag
            binding.teamTwoNameVar = match.teamTwoName
            binding.teamTwoFlagVar = match.teamTwoFlag
        }
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val group = getItem(position)
        holder.bind(group)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            MatchTableBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MatchWithTeamsDetailsEntity>() {
        override fun areContentsTheSame(oldItem: MatchWithTeamsDetailsEntity, newItem: MatchWithTeamsDetailsEntity): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: MatchWithTeamsDetailsEntity, newItem: MatchWithTeamsDetailsEntity): Boolean {
            return oldItem.key == newItem.key
        }
    }
}