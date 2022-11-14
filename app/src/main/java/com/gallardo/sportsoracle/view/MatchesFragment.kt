package com.gallardo.sportsoracle.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.gallardo.sportsoracle.R
import com.gallardo.sportsoracle.databinding.FragmentMatchesBinding
import com.gallardo.sportsoracle.databinding.FragmentMatchesDateBinding
import com.gallardo.sportsoracle.view.rvadapter.MatchesListAdapter
import com.gallardo.sportsoracle.viewmodels.MatchesViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MatchesFragment : Fragment() {
    private val matchesViewModel: MatchesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMatchesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.matchesViewModel = matchesViewModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        matchesViewModel.matchDates.observe(viewLifecycleOwner) {
            val adapter = MatchesTabsAdapter(this, it)
            val viewPager : ViewPager2 = view.findViewById(R.id.pager)
            viewPager.adapter = adapter
            val tabLayout : TabLayout = view.findViewById(R.id.tab_layout)

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                val date = SimpleDateFormat("yyyyMMddHHmm").parse(it[position])
                tab.text = date.toString("MM/dd/yyyy")
            }.attach()
        }
    }

}

class MatchesTabsAdapter(private val parentFragment: Fragment, private val dates: List<String>) : FragmentStateAdapter(parentFragment) {
    override fun getItemCount(): Int {
        return dates.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = MatchesFragmentByDate()
        val bundle = Bundle()
        bundle.putString(parentFragment.getString(R.string.argument_date), dates[position])
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
            matchesViewModel.matchesWithTeamDetails(date).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                (binding.matchList.adapter as MatchesListAdapter).submitList(it)
            })

        }

        return binding.root
    }
}