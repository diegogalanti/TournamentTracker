package com.gallardo.sportsoracle.view.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gallardo.sportsoracle.databinding.MatchTableBinding
import com.gallardo.sportsoracle.model.Match
import com.gallardo.sportsoracle.model.MatchWithTeamsDetails
import com.gallardo.sportsoracle.viewmodels.MatchesViewModel

class MatchesListAdapter : ListAdapter<MatchWithTeamsDetails, MatchesListAdapter.MatchViewHolder>(DiffCallback) {

    class MatchViewHolder(private val binding: MatchTableBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (match: MatchWithTeamsDetails) {
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

    companion object DiffCallback : DiffUtil.ItemCallback<MatchWithTeamsDetails>() {
        override fun areContentsTheSame(oldItem: MatchWithTeamsDetails, newItem: MatchWithTeamsDetails): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: MatchWithTeamsDetails, newItem: MatchWithTeamsDetails): Boolean {
            return oldItem.key == newItem.key
        }
    }
}