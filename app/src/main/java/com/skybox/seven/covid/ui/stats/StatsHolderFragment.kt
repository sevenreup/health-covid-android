package com.skybox.seven.covid.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentStatsHolderBinding
import com.skybox.seven.covid.helpers.CircularSearchView
import com.skybox.seven.covid.ui.MainViewModel
import com.skybox.seven.covid.ui.stats.countries.CountriesFragment
import com.skybox.seven.covid.ui.stats.countries.CountriesViewModel
import com.skybox.seven.covid.ui.stats.overview.StatisticsFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StatsHolderFragment : Fragment(), CircularSearchView.CircularCallbacks {
    private lateinit var binding: FragmentStatsHolderBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val countriesModel: CountriesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentStatsHolderBinding.inflate(inflater, container, false)
        binding.worldViewPager.adapter = StatsAdapter(this)
        TabLayoutMediator(binding.worldTabLayout, binding.worldViewPager) { tab, position ->
            tab.setText(if (position == 0) R.string.overview else R.string.countries)
        }.attach()
        binding.searchView.setCircularCallbacks(this)
        viewModel.closeSearch.observe(viewLifecycleOwner, Observer {
            binding.searchView.closeSearch()
            binding.motion.transitionToState(R.id.start)
            viewModel.searchOpened.value = false
        })
        return binding.root
    }

    override fun onSearchOpen() {
        viewModel.searchOpened.value = true
        binding.motion.transitionToState(R.id.search)
        binding.worldViewPager.currentItem = 1
    }

    override fun onSearchClose() {
        viewModel.searchOpened.value = false
        binding.motion.transitionToState(R.id.start)
    }

    override fun onTextChanged(text: String) {
        if (text.count() > 0) {
            countriesModel.searchText.value = text
        } else {
            countriesModel.rebuild.value = true
        }
    }

    override fun onPause() {
        viewModel.searchOpened.value = false
        super.onPause()
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