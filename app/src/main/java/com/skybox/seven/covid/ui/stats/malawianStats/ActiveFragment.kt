package com.skybox.seven.covid.ui.stats.malawianStats

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.skybox.seven.covid.components.BarChartRoundedRenderer
import com.skybox.seven.covid.databinding.FragmentActiveBinding

class ActiveFragment : Fragment() {
    private lateinit var binding: FragmentActiveBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentActiveBinding.inflate(inflater, container, false)
        setUpBarGraph()
        setUpData()
        binding.all.setOnClickListener { setUpData() }
        binding.one.setOnClickListener { setUpSingleData("Active", Color.RED) }
        return binding.root
    }

    private fun setUpBarGraph() {
        with(binding.activeChart) {
            animateX(1400)
            setPinchZoom(false)
            isDragEnabled = false
            setScaleEnabled(false)
            isDoubleTapToZoomEnabled = false
            renderer = BarChartRoundedRenderer(this, this.animator, this.viewPortHandler, 7F)
            setFitBars(true)
            description.isEnabled = false
            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onNothingSelected() {
                }

                override fun onValueSelected(e: Entry?, h: Highlight?) {
                }

            })
        }
    }

    private fun setUpData() {
        val bars = arrayListOf(
                BarEntry(1F, floatArrayOf(20F, 20F, 5F)),
                BarEntry(2F, floatArrayOf(20F, 20F, 5F)),
                BarEntry(3F, floatArrayOf(40F, 50F, 51F)),
                BarEntry(4F, floatArrayOf(30F, 20F, 6F)),
                BarEntry(5F, floatArrayOf(60F, 20F, 70F)),
                BarEntry(6F, floatArrayOf(40F, 29F, 58F)),
                BarEntry(7F, floatArrayOf(60F, 23F, 85F))
        )
        val barDataSet = BarDataSet(bars, "stuff")
        barDataSet.stackLabels = arrayOf("active", "recovered", "deaths")
        barDataSet.setColors(
                Color.RED,
                Color.YELLOW,
                Color.GREEN
        )
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16F
        barDataSet.setDrawValues(false)

        binding.activeChart.data = BarData(barDataSet)
        binding.activeChart.invalidate()
    }

    private fun setUpSingleData(label: String, color: Int) {
        val bars = arrayListOf(
                BarEntry(1F, 20F),
                BarEntry(2F, 20F),
                BarEntry(3F, 50F),
                BarEntry(4F, 20F),
                BarEntry(5F, 20F),
                BarEntry(6F, 58F),
                BarEntry(7F, 23F)
        )
        val barDataSet = BarDataSet(bars, "stuff")
        barDataSet.label = label
        barDataSet.color = color
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16F
        barDataSet.setDrawValues(false)

        binding.activeChart.data = BarData(barDataSet)
        binding.activeChart.invalidate()
    }

}