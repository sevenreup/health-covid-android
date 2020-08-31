package com.skybox.seven.covid.ui.stats.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.*
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentStatsBarChatBinding
import com.skybox.seven.covid.helpers.DataState
import com.skybox.seven.covid.model.HistoricalResult
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "StatsBarchartFragment"

@AndroidEntryPoint
class CountryStatsChatFragment : Fragment() {
    private lateinit var binding: FragmentStatsBarChatBinding
    private lateinit var args: CountryStatsChatFragmentArgs
    val viewModel: CountryStatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = CountryStatsChatFragmentArgs.fromBundle(requireArguments())
        viewModel.countryStats.value = args.country
        viewModel.getWeekData(args.country.country)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentStatsBarChatBinding.inflate(inflater, container, false)
        binding.chartType.addOnButtonCheckedListener { _, _, isChecked ->
            if (isChecked) {
                graphLoad()
            }

        }
        binding.timeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener  {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onTimelineChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        viewModel.allStats.observe(viewLifecycleOwner, Observer {
            when(it.dataState) {
                DataState.SUCCESS ->
                {
                    if (binding.chartType.checkedButtonId == R.id.bar) showBar(it.data!!) else showLine(it.data!!)
                }

                DataState.LOADING -> {
                    //todo: show loading
                }
                else -> {
                 // todo: show error
                }
            }

        })
        return binding.root
    }

    private fun graphLoad() {
        if (viewModel.allStats.value != null) {
            if (viewModel.allStats.value?.dataState == DataState.SUCCESS) {
                if (binding.chartType.checkedButtonId == R.id.bar) showBar(viewModel.allStats.value?.data!!) else showLine(viewModel.allStats.value?.data!!)
            }
        }
    }

    fun onTimelineChanged() {
        when (binding.timeSpinner.selectedItem as String) {
            getString(R.string.week) ->
                viewModel.getWeekData(args.country.country)
            getString(R.string.month) ->
                viewModel.getMothData(args.country.country)
            else ->
                viewModel.getYearData(args.country.country)

        }
    }

    private fun showBar(data: HistoricalResult) {
        val recoveredValues: ArrayList<BarEntry> = ArrayList()
        val criticalValues: ArrayList<BarEntry> = ArrayList()
        val activeValues: ArrayList<BarEntry> = ArrayList()

        data.recovered.forEachIndexed { index, timeLineResult ->
            recoveredValues.add(BarEntry(index.toFloat(), timeLineResult.cases.toFloat()))
        }

        data.deaths.forEachIndexed { index, timeLineResult ->
            criticalValues.add(BarEntry(index.toFloat(), timeLineResult.cases.toFloat()))
        }

        data.cases.forEachIndexed { index, timeLineResult ->
            activeValues.add(BarEntry(index.toFloat(), timeLineResult.cases.toFloat()))
        }
        val recoveredSet = BarDataSet(recoveredValues, null)
                .apply {
                    color = ContextCompat.getColor(requireContext(), R.color.colorAccent)
                    setDrawValues(false)
                }

        val criticalSet = BarDataSet(criticalValues, null)
                .apply {
                    color = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
                    setDrawValues(false)
                }

        val activeSet = BarDataSet(activeValues, null)
                .apply {
                    color = ContextCompat.getColor(requireContext(), R.color.color_on_secondary)
                    setDrawValues(false)
                }
        with(binding.barChart) {
            this.data = BarData(criticalSet, recoveredSet, activeSet)

            invalidate()
        }
    }

    private fun showLine(data: HistoricalResult) {
        val recoveredValues: ArrayList<Entry> = ArrayList()
        val criticalValues: ArrayList<Entry> = ArrayList()
        val activeValues: ArrayList<Entry> = ArrayList()

        data.recovered.forEachIndexed { index, timeLineResult ->
            recoveredValues.add(Entry(index.toFloat(), timeLineResult.cases.toFloat()))
        }

        data.deaths.forEachIndexed { index, timeLineResult ->
            criticalValues.add(Entry(index.toFloat(), timeLineResult.cases.toFloat()))
        }

        data.cases.forEachIndexed { index, timeLineResult ->
            activeValues.add(Entry(index.toFloat(), timeLineResult.cases.toFloat()))
        }

        val recoveredSet = LineDataSet(recoveredValues, null)
                .apply {
                    color = ContextCompat.getColor(requireContext(), R.color.colorAccent)
                    setDrawValues(false)
                }

        val criticalSet = LineDataSet(criticalValues, null)
                .apply {
                    color = ContextCompat.getColor(requireContext(), R.color.color_error)
                    setDrawValues(false)
                }

        val activeSet = LineDataSet(activeValues, null)
                .apply {
                    color = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
                    setDrawValues(false)
                }

        binding.lineChat.data = LineData(criticalSet, recoveredSet, activeSet)
        binding.lineChat.invalidate()
    }
}



