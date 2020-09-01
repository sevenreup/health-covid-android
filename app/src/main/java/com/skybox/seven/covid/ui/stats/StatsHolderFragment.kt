package com.skybox.seven.covid.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentStatsHolderBinding
import com.skybox.seven.covid.ui.stats.countries.CountriesFragment
import com.skybox.seven.covid.ui.stats.overview.StatisticsFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StatsHolderFragment : Fragment() {
    private lateinit var binding: FragmentStatsHolderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentStatsHolderBinding.inflate(inflater, container, false)
        binding.worldViewPager.adapter = StatsAdapter(this)
        TabLayoutMediator(binding.worldTabLayout, binding.worldViewPager) { tab, position ->
            tab.setText(if (position == 0) R.string.overview else R.string.countries)
        }.attach()
        return binding.root
    }
}

class StatsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) =
            if (position == 0) {
                StatisticsFragment()
            } else {
                CountriesFragment()
            }

}