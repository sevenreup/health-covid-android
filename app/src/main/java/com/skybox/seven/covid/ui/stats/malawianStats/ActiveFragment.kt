package com.skybox.seven.covid.ui.stats.malawianStats

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.skybox.seven.covid.R

class ActiveFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_active, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        barGraph()
    }
    private fun barGraph(){

        val colors:ArrayList<Int> = ArrayList()
        colors.add(Color.RED)
        colors.add(Color.GREEN)
        colors.add(Color.YELLOW)
        colors.add(Color.GRAY)
        colors.add(Color.LTGRAY)
        colors.add(Color.MAGENTA)
        colors.add(Color.LTGRAY)

        val chart = view?.findViewById<BarChart>(R.id.activeChart)
        val bars: ArrayList<BarEntry> = ArrayList()

        bars.add(BarEntry(1F, 10F))
        bars.add(BarEntry(2F, 20F))
        bars.add(BarEntry(3F, 30F))
        bars.add(BarEntry(4F, 40F))
        bars.add(BarEntry(5F, 50F))
        bars.add(BarEntry(6F, 60F))
        bars.add(BarEntry(7F, 70F))
        val barDataSet = BarDataSet(bars, "Active")
        barDataSet.colors = colors
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16F
        val barData = BarData(barDataSet)


        chart?.setFitBars(true)
        chart?.data = barData
        chart?.description?.text = ""
        chart?.animateY(2000)
    }


}