package com.skybox.seven.covid.ui.stats.country

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentStatsBarChatBinding
import com.skybox.seven.covid.helpers.DataState
import com.skybox.seven.covid.model.HistoricalResult
import com.skybox.seven.covid.util.shortValue
import com.skybox.seven.covid.util.takeLast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner
        binding.chartType.addOnButtonCheckedListener { _, _, isChecked ->
            if (isChecked) {
                graphLoad()
            }

        }
        binding.timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onTimelineChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        viewModel.allStats.observe(viewLifecycleOwner, Observer {
            when (it.dataState) {
                DataState.SUCCESS -> {
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
        initBar()
        initLine()
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
        moveBar()
        val recoveredValues: ArrayList<BarEntry> = ArrayList()
        val criticalValues: ArrayList<BarEntry> = ArrayList()
        val activeValues: ArrayList<BarEntry> = ArrayList()

        data.cases.forEachIndexed { index, timeLineResult ->
            activeValues.add(BarEntry(index.toFloat(), timeLineResult.cases.toFloat()))
            criticalValues.add(BarEntry(index.toFloat(), timeLineResult.deaths.toFloat()))
            recoveredValues.add(BarEntry(index.toFloat(), timeLineResult.recovered.toFloat()))
        }

        val activeSet = BarDataSet(activeValues, getString(R.string.active))
                .apply {
                    setDrawValues(false)
                    color = ContextCompat.getColor(requireContext(), R.color.active)
                }

        val recSet = BarDataSet(recoveredValues, getString(R.string.recovered))
                .apply {
                    setDrawValues(false)
                    color = ContextCompat.getColor(requireContext(), R.color.recovered)
                }
        val crSet = BarDataSet(criticalValues, getString(R.string.deaths))
                .apply {
                    setDrawValues(false)
                    color = ContextCompat.getColor(requireContext(), R.color.deaths)
                }
        binding.barChart.data = BarData(activeSet, recSet, crSet)
        binding.barChart.invalidate()
    }

    private fun showLine(data: HistoricalResult) {
        moveLine()
        val recoveredValues: ArrayList<Entry> = ArrayList()
        val criticalValues: ArrayList<Entry> = ArrayList()
        val activeValues: ArrayList<Entry> = ArrayList()

        data.cases.forEachIndexed { index, timeLineResult ->
            activeValues.add(Entry(index.toFloat(), timeLineResult.cases.toFloat()))
            criticalValues.add(Entry(index.toFloat(), timeLineResult.deaths.toFloat()))
            recoveredValues.add(Entry(index.toFloat(), timeLineResult.recovered.toFloat()))
        }

        val recoveredSet = LineDataSet(recoveredValues, getString(R.string.recovered))
                .apply {
                    color = ContextCompat.getColor(requireContext(), R.color.recovered)
                    setDrawValues(false)
                }

        val criticalSet = LineDataSet(criticalValues, getString(R.string.deaths))
                .apply {
                    color = ContextCompat.getColor(requireContext(), R.color.deaths)
                    setDrawValues(false)
                }

        val activeSet = LineDataSet(activeValues, getString(R.string.active))
                .apply {
                    color = ContextCompat.getColor(requireContext(), R.color.active)
                    setDrawValues(false)
                }

        binding.lineChat.data = LineData(activeSet, recoveredSet, criticalSet)
        binding.lineChat.invalidate()
    }

    private fun moveLine() {
        val set = ConstraintSet()
        set.clone(binding.carousel)
        set.connect(R.id.line_chat, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        set.connect(R.id.line_chat, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)

        set.connect(R.id.bar_chart, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.clear(R.id.bar_chart, ConstraintSet.START)

        set.applyTo(binding.carousel)
    }

    private fun moveBar() {
        val set = ConstraintSet()
        set.clone(binding.carousel)
        set.connect(R.id.bar_chart, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        set.connect(R.id.bar_chart, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)

        set.connect(R.id.line_chat, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.clear(R.id.line_chat, ConstraintSet.START)

        set.applyTo(binding.carousel)
    }

    private fun initBar() {
        with(binding.barChart) {
            description.isEnabled = false
            legend.isEnabled = true

            isDragDecelerationEnabled = false
            setTouchEnabled(false)

            xAxis.axisMinimum = 0f
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setLabelCount(2, true)

            axisLeft.axisMinimum = 0f
            axisLeft.setDrawAxisLine(false)
            axisLeft.setLabelCount(5, true)

            axisRight.isEnabled = false

            axisLeft.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return if (value <= 0) "" else value.toLong().shortValue()
                }
            }

            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val parse = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                    return if (value <= 0) {
                        parse.format(Calendar.getInstance().takeLast(7))
                    } else {
                        parse.format(Date())
                    }
                }
            }
        }
    }

    private fun initLine() {

    }

    companion object {
        private const val DATE_FORMAT = "dd/MM"
    }
}



