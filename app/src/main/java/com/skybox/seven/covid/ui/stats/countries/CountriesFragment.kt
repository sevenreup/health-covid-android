package com.skybox.seven.covid.ui.stats.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.skybox.seven.covid.StatsDirections
import com.skybox.seven.covid.databinding.FragmentCountriesBinding
import com.skybox.seven.covid.epoxy.stats.CountryCallbacks
import com.skybox.seven.covid.epoxy.stats.CountryController
import com.skybox.seven.covid.model.CountryStat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : Fragment(), CountryCallbacks {
    private lateinit var binding: FragmentCountriesBinding
    val viewModel: CountriesViewModel by viewModels()
    private val controller = CountryController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.allCountriesData.observe(this, Observer {
            controller.setData(false, it)
        })
        viewModel.getAllCountries()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        binding.countriesRecycler.setController(controller)
        return binding.root
    }

    override fun onCountryClicked(stat: CountryStat, view: View) {
        val action = StatsDirections.toCountryStatsFragment(stat)
        findNavController().navigate(action)
    }
}