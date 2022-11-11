package com.gallardo.sportsoracle.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.gallardo.sportsoracle.R
import com.gallardo.sportsoracle.databinding.FragmentMatchesBinding
import com.gallardo.sportsoracle.databinding.FragmentMatchesDateBinding
import com.gallardo.sportsoracle.view.rvadapter.MatchesListAdapter
import com.gallardo.sportsoracle.viewmodels.MatchesViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.util.*


class MatchesFragment : Fragment() {
    private val matchesViewModel: MatchesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMatchesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.matchesViewModel = matchesViewModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MatchesTabsAdapter(this, matchesViewModel)
        val viewPager : ViewPager2 = view.findViewById(R.id.pager)
        viewPager.adapter = adapter

        val tabLayout : TabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val date = SimpleDateFormat("yyyyMMddHHmm").parse(matchesViewModel.matchDates[position])
            tab.text = date.toString("MM/dd/yyyy")
        }.attach()
    }

}

class MatchesTabsAdapter(private val parentFragmant: Fragment, private val matchesViewModel: MatchesViewModel) : FragmentStateAdapter(parentFragmant) {
    override fun getItemCount(): Int {
        return matchesViewModel.matchDates.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = MatchesFragmentByDate()
        val bundle = Bundle()
        bundle.putString(parentFragmant.getString(R.string.argument_date), matchesViewModel.matchDates[position])
        fragment.arguments = bundle
        return fragment
    }
}

class MatchesFragmentByDate : Fragment() {
    private val matchesViewModel: MatchesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMatchesDateBinding.inflate(inflater, container, false)
        binding.matchList.adapter = MatchesListAdapter()
        val date = this.arguments?.getString(getString(R.string.argument_date))
        date?.let {
            Log.e("teste", date)
            (binding.matchList.adapter as MatchesListAdapter).submitList(matchesViewModel.getMatchesWithTeamsDetails(date))
        }

        return binding.root
    }
}