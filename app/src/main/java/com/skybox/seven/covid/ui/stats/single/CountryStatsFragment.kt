package com.skybox.seven.covid.ui.stats.single

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentStatsBarChatBinding
import com.skybox.seven.covid.helpers.DataState
import com.skybox.seven.covid.helpers.DateHelper
import com.skybox.seven.covid.helpers.TextFormatter.formatNumber
import com.skybox.seven.covid.model.CountryStat
import com.skybox.seven.covid.model.HistoricalResult
import com.skybox.seven.covid.ui.stats.overview.StatisticsFragment
import com.skybox.seven.covid.util.shortValue
import com.skybox.seven.covid.util.takeLast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


private const val TAG = "StatsBarchartFragment"

@AndroidEntryPoint
class CountryStatsChatFragment : Fragment() {
    private lateinit var binding: FragmentStatsBarChatBinding
    private lateinit var args: CountryStatsChatFragmentArgs
    val viewModel: CountryStatsViewModel by viewModels()
    private var active = WEEK

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

        binding.errorHolder.onclick = View.OnClickListener {
            binding.refresh.isRefreshing = false
            when (active) {
                ALL -> viewModel.getYearData(args.country.country)
                WEEK -> viewModel.getWeekData(args.country.country)
                else -> viewModel.getMothData(args.country.country)
            }
        }


        binding.chartType.addOnButtonCheckedListener { _, _, isChecked ->
            if (isChecked) {
                graphLoad()
            }

        }
        viewModel.countryStats.observe(viewLifecycleOwner, Observer {
            binding.countryTotals.apply {
                title = "${args.country.country} ${getString(R.string.cases)}"
                cases = it.cases.formatNumber()
                active = it.active.formatNumber()
                deaths = it.deaths.formatNumber()
                recovered = it.recovered.formatNumber()
                date = DateHelper.formatDate(it.updated.toString())
                loading = false
                error = false
                problems.visibility = View.VISIBLE
                share.visibility = View.VISIBLE
                share.setOnClickListener {
                    StatisticsFragment.shareShoot(this, requireContext())
                }
            }
            countryChat(it)
        })
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
                    binding.refresh.isRefreshing = false
                    viewModel.networkError.value = false
                    if (binding.chartType.checkedButtonId == R.id.bar) showBar(it.data) else showLine(it.data)
                }

                DataState.LOADING -> {
                    //todo: show loading
                }
                else -> {
                    binding.refresh.isRefreshing = false
                    viewModel.networkError.value = true

                    if (binding.chartType.checkedButtonId == R.id.bar) showBar(it.data) else showLine(it.data)
                }
            }
        })

        initBar()
        initLine()

        binding.refresh.setOnRefreshListener {
            when (active) {
                ALL -> viewModel.getYearData(args.country.country)
                WEEK -> viewModel.getWeekData(args.country.country)
                else -> viewModel.getMothData(args.country.country)
            }
        }
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
        active = when (binding.timeSpinner.selectedItem as String) {
            getString(R.string.week) -> {
                viewModel.getWeekData(args.country.country)
                WEEK
            }
            getString(R.string.month) -> {
                viewModel.getMothData(args.country.country)
                MONTH
            }
            else -> {
                viewModel.getYearData(args.country.country)
                ALL
            }
        }
    }

    private fun showBar(data: HistoricalResult?) {
        var barData: BarData? = null
        moveBar()

        if (data != null) {
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
            barData = BarData(activeSet, recSet, crSet)
        }

        binding.barChart.data = barData
        binding.barChart.invalidate()
    }

    private fun showLine(data: HistoricalResult?) {
        moveLine()
        var lineData: LineData? = null

        if (data != null) {
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
                        lineStyle(this, ContextCompat.getColor(requireContext(), R.color.recovered))
                    }

            val criticalSet = LineDataSet(criticalValues, getString(R.string.deaths))
                    .apply {
                        color = ContextCompat.getColor(requireContext(), R.color.deaths)
                        setDrawValues(false)
                        lineStyle(this, ContextCompat.getColor(requireContext(), R.color.deaths))
                    }

            val activeSet = LineDataSet(activeValues, getString(R.string.active))
                    .apply {
                        color = ContextCompat.getColor(requireContext(), R.color.active)
                        setDrawValues(false)
                        lineStyle(this, ContextCompat.getColor(requireContext(), R.color.active))
                    }
            lineData = LineData(activeSet, recoveredSet, criticalSet)
        }



        binding.lineChat.data = lineData
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

            axisLeft.axisMinimum = 0f
            axisLeft.setDrawAxisLine(false)
            axisLeft.setLabelCount(5, true)

            axisRight.isEnabled = false

            axisLeft.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return if (value <= 0) "" else value.toLong().shortValue()
                }
            }
            with(xAxis){
                axisMinimum = 0f
                setDrawGridLines(false)
                position = XAxis.XAxisPosition.BOTTOM
                setLabelCount(2, true)

                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String  {
                        return ""
                    }
                }
            }
        }
    }

    private fun initLine() {
        with(binding.lineChat) {
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)

            description.isEnabled = false

            axisRight.isEnabled = false
            xAxis.run {
                position = XAxis.XAxisPosition.BOTTOM

                valueFormatter = object : ValueFormatter() {
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
    }

    private fun countryChat(countryStat: CountryStat) {
        val barDataSet = PieDataSet(
                listOf(
                        PieEntry(countryStat.deaths.toFloat(), requireContext().getString(R.string.deaths)),
                        PieEntry(countryStat.active.toFloat(), requireContext().getString(R.string.active)),
                        PieEntry(countryStat.recovered.toFloat(), requireContext().getString(R.string.recovered))
                ),
                "key"
        )
        StatisticsFragment.setUpBar(barDataSet, requireContext(), binding.countryTotals.worldChat)
    }

    fun goBack() {
        findNavController().navigateUp()
    }

    companion object {
        private const val DATE_FORMAT = "dd/MM"
        const val WEEK = 0
        const val ALL = 1
        const val MONTH = 2

        fun lineStyle(data: LineDataSet, color: Int) {
            with(data) {
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawCircles(false)
                setDrawFilled(true)

                fillColor = color
                fillAlpha = 150

                lineWidth = 2f
            }
        }
    }
}



