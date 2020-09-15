package com.skybox.seven.covid.ui.stats.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.addGlidePreloader
import com.airbnb.epoxy.glidePreloader
import com.bumptech.glide.RequestManager
import com.skybox.seven.covid.GlideApp
import com.skybox.seven.covid.R
import com.skybox.seven.covid.StatsDirections
import com.skybox.seven.covid.databinding.FragmentCountriesBinding
import com.skybox.seven.covid.epoxy.stats.CountryCallbacks
import com.skybox.seven.covid.epoxy.stats.CountryController
import com.skybox.seven.covid.epoxy.stats.CountryModel_
import com.skybox.seven.covid.model.CountryStat
import com.skybox.seven.covid.util.SpaceItemDecorator
import com.skybox.seven.covid.util.loadImage
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CountriesFragment : Fragment(), CountryCallbacks {
    private lateinit var binding: FragmentCountriesBinding
    val viewModel: CountriesViewModel by activityViewModels()
    private val controller = CountryController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.allCountriesData.observe(this, Observer {
            viewModel.filteredList.value = it
        })
        viewModel.filteredList.observe(this, Observer {
            controller.setData(false, it)
        })
        viewModel.close.observe(this, Observer {
            viewModel.filteredList.value = viewModel.allCountriesData.value
        })
        viewModel.getAllCountries()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        binding.countriesRecycler.apply {
            setController(controller)
            addItemDecoration(SpaceItemDecorator(resources.getDimensionPixelSize(R.dimen.card_seperator_margin), true, true))
            addGlidePreloader(
                    GlideApp.with(this),
                    preloader = glidePreloader { requestManager: RequestManager, model: CountryModel_, _->
                        requestManager.loadImage(model.flag, true)
                    }
            )
        }
        viewModel.searchText.observe(viewLifecycleOwner, Observer {text ->
            if (viewModel.allCountriesData.value != null) {
                val filteredList = viewModel.allCountriesData.value!!.filter {
                    val country = it.country.toLowerCase(Locale.getDefault())
                    val currentText = text.toLowerCase(Locale.getDefault())
                    country.contains(currentText)
                }
                viewModel.filteredList.value = filteredList
            }
        })
        viewModel.rebuild.observe(viewLifecycleOwner, Observer {
            viewModel.filteredList.value = viewModel.allCountriesData.value
        })
        return binding.root
    }

    override fun onCountryClicked(stat: CountryStat, view: View) {
        val action = StatsDirections.toCountryStatsFragment(stat)
        findNavController().navigate(action)
        viewModel.rebuild.value = true
    }
}