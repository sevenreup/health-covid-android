package com.skybox.seven.covid.ui.stats

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.skybox.seven.covid.R


@Suppress("DEPRECATION")
class StatisticsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        view?.findViewById<TextView>(R.id.mw_situation)?.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment_to_statsBarChatFragment)
            this.requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        view?.findViewById<TextView>(R.id.view_countries)?.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment_to_countriesFragment)
        }

        return inflater.inflate(R.layout.fragment_stats, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        worldChat()
    }

    private fun worldChat(){
        val colors:ArrayList<Int> = ArrayList()
        colors.add(this.resources.getColor(R.color.reply_orange_400))
        colors.add(this.resources.getColor(R.color.reply_green_100))
        colors.add(this.resources.getColor(R.color.reply_red_400))


        val chart = view?.findViewById<PieChart>(R.id.worldChat)
        val bars: ArrayList<PieEntry> = ArrayList()

        bars.add(PieEntry(50F, ""))
        bars.add(PieEntry(30F, ""))
        bars.add(PieEntry(20F, ""))

        val barDataSet = PieDataSet(bars, "Key")
        barDataSet.colors = colors
        barDataSet.valueTextColor = Color.WHITE
        barDataSet.valueTextSize = 16F
        val barData = PieData(barDataSet)


        chart?.data = barData
        chart?.centerText = "Cases"
        chart?.description?.isEnabled = false
        chart?.animateY(2000)
    }

}
