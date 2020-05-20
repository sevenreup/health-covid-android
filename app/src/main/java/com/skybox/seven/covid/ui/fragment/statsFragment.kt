package com.skybox.seven.covid.ui.fragment

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter

import com.skybox.seven.covid.R
import kotlinx.android.synthetic.main.fragment_stats.*


class statsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        caseData()
        deathsData()
        activeData()
        recoveredData()
        affectedData()

    }

    private fun affectedData(){
        val elapse: Long = 5000

        val animator: ValueAnimator = ValueAnimator.ofInt(0, 1024)
        animator.duration = elapse
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener (){
            num_affected.text = animator.animatedValue.toString()
        })
        animator.start()

    }

    private fun deathsData(){
        val elapse: Long = 5000

        val animator: ValueAnimator = ValueAnimator.ofInt(0, 3)
        animator.duration = elapse
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener (){
            num_deaths.text = animator.animatedValue.toString()
        })
        animator.start()

    }

    private fun activeData(){
        val elapse: Long = 5000

        val animator: ValueAnimator = ValueAnimator.ofInt(0, 400)
        animator.duration = elapse
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener (){
            num_active.text = animator.animatedValue.toString()
        })
        animator.start()

    }

    private fun recoveredData(){
        val elapse: Long = 5000

        val animator: ValueAnimator = ValueAnimator.ofInt(0, 30)
        animator.duration = elapse
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener (){
            num_recovered.text = animator.animatedValue.toString()
        })
        animator.start()

    }


    private fun caseData() {

        val barChartView = view?.findViewById<BarChart>(R.id.caseChart)

        val barWidth: Float
        val barSpace: Float
        val groupSpace: Float
        val groupCount = 12

        barWidth = 0.15f
        barSpace = 0.07f
        groupSpace = 0.56f

        var xAxisValues = ArrayList<String>()
        xAxisValues.add("Jan")
        xAxisValues.add("Feb")
        xAxisValues.add("Mar")
        xAxisValues.add("Apr")
        xAxisValues.add("May")
        xAxisValues.add("June")
        xAxisValues.add("Jul")
        xAxisValues.add("Aug")
        xAxisValues.add("Sep")
        xAxisValues.add("Oct")
        xAxisValues.add("Nov")
        xAxisValues.add("Dec")

        val yValueGroup1 = ArrayList<BarEntry>()
        val yValueGroup2 = ArrayList<BarEntry>()


        val barDataSet1: BarDataSet
        val barDataSet2: BarDataSet


        yValueGroup1.add(BarEntry(1f, floatArrayOf(9.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(1f, floatArrayOf(2.toFloat(), 7.toFloat())))


        yValueGroup1.add(BarEntry(2f, floatArrayOf(3.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(2f, floatArrayOf(4.toFloat(), 15.toFloat())))

        yValueGroup1.add(BarEntry(3f, floatArrayOf(3.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(3f, floatArrayOf(4.toFloat(), 15.toFloat())))

        yValueGroup1.add(BarEntry(4f, floatArrayOf(3.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(4f, floatArrayOf(4.toFloat(), 15.toFloat())))


        yValueGroup1.add(BarEntry(5f, floatArrayOf(9.toFloat(), 3.toFloat())))
        yValueGroup2.add(BarEntry(5f, floatArrayOf(10.toFloat(), 6.toFloat())))

        yValueGroup1.add(BarEntry(6f, floatArrayOf(11.toFloat(), 1.toFloat())))
        yValueGroup2.add(BarEntry(6f, floatArrayOf(12.toFloat(), 2.toFloat())))


        yValueGroup1.add(BarEntry(7f, floatArrayOf(11.toFloat(), 7.toFloat())))
        yValueGroup2.add(BarEntry(7f, floatArrayOf(12.toFloat(), 12.toFloat())))


        yValueGroup1.add(BarEntry(8f, floatArrayOf(11.toFloat(), 9.toFloat())))
        yValueGroup2.add(BarEntry(8f, floatArrayOf(12.toFloat(), 8.toFloat())))


        yValueGroup1.add(BarEntry(9f, floatArrayOf(11.toFloat(), 13.toFloat())))
        yValueGroup2.add(BarEntry(9f, floatArrayOf(12.toFloat(), 12.toFloat())))

        yValueGroup1.add(BarEntry(10f, floatArrayOf(11.toFloat(), 2.toFloat())))
        yValueGroup2.add(BarEntry(10f, floatArrayOf(12.toFloat(), 7.toFloat())))

        yValueGroup1.add(BarEntry(11f, floatArrayOf(11.toFloat(), 6.toFloat())))
        yValueGroup2.add(BarEntry(11f, floatArrayOf(12.toFloat(), 5.toFloat())))

        yValueGroup1.add(BarEntry(12f, floatArrayOf(11.toFloat(), 2.toFloat())))
        yValueGroup2.add(BarEntry(12f, floatArrayOf(12.toFloat(), 3.toFloat())))


        barDataSet1 = BarDataSet(yValueGroup1, "")
        barDataSet1.setColors(Color.BLUE, Color.RED)
        barDataSet1.label = "2019"
        barDataSet1.setDrawIcons(false)
        barDataSet1.setDrawValues(false)



        barDataSet2 = BarDataSet(yValueGroup2, "")

        barDataSet2.label = "2020"
        barDataSet2.setColors(Color.YELLOW, Color.RED)

        barDataSet2.setDrawIcons(false)
        barDataSet2.setDrawValues(false)

        val barData = BarData(barDataSet1, barDataSet2)

        barChartView?.description?.isEnabled = false
        barChartView?.description?.textSize = 0f
        barData.setValueFormatter(LargeValueFormatter())
        barChartView?.setData(barData)
        barChartView?.getBarData()?.setBarWidth(barWidth)
        barChartView?.getXAxis()?.setAxisMinimum(0f)
        barChartView?.getXAxis()?.setAxisMaximum(12f)
        barChartView?.groupBars(0f, groupSpace, barSpace)

        barChartView?.getData()?.setHighlightEnabled(false)
        barChartView?.invalidate()


        val legend = barChartView?.legend
        legend?.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
        legend?.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        legend?.setOrientation(Legend.LegendOrientation.HORIZONTAL)
        legend?.setDrawInside(false)

        val legenedEntries = arrayListOf<LegendEntry>()

        legenedEntries.add(LegendEntry("2019", Legend.LegendForm.SQUARE, 8f, 8f, null, Color.RED))
        legenedEntries.add(LegendEntry("2020", Legend.LegendForm.SQUARE, 8f, 8f, null, R.color.primary))

        legend?.setCustom(legenedEntries)

        legend?.setYOffset(2f)
        legend?.setXOffset(2f)
        legend?.setYEntrySpace(0f)
        legend?.setTextSize(5f)

        val xAxis = barChartView?.getXAxis()
        xAxis?.setGranularity(1f)
        xAxis?.setGranularityEnabled(true)
        xAxis?.setCenterAxisLabels(true)
        xAxis?.setDrawGridLines(false)
        xAxis?.textSize = 9f

        xAxis?.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis?.setValueFormatter(IndexAxisValueFormatter(xAxisValues))

        xAxis?.setLabelCount(12)
        xAxis?.mAxisMaximum = 12f
        xAxis?.setCenterAxisLabels(true)
        xAxis?.setAvoidFirstLastClipping(true)
        xAxis?.spaceMin = 4f
        xAxis?.spaceMax = 4f

        barChartView?.setVisibleXRangeMaximum(12f)
        barChartView?.setVisibleXRangeMinimum(12f)
        barChartView?.setDragEnabled(true)

        //Y-axis
        barChartView?.getAxisRight()?.setEnabled(false)
        barChartView?.setScaleEnabled(true)

        val leftAxis = barChartView?.getAxisLeft()
        leftAxis?.setValueFormatter(LargeValueFormatter())
        leftAxis?.setDrawGridLines(false)
        leftAxis?.setSpaceTop(1f)
        leftAxis?.setAxisMinimum(0f)


        barChartView?.data = barData
        barChartView?.setVisibleXRange(1f, 12f)
    }

}
