package com.skybox.seven.covid.ui.stats

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentStatsBinding


class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        binding.mwContainer.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment_to_statsBarChatFragment)
            this.requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.viewCountries.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment_to_countriesFragment)
        }
        worldChat()
        return binding.root

    }

    private fun worldChat() {
        val barDataSet = PieDataSet(
                listOf(
                        PieEntry(50F, getString(R.string.deaths)),
                        PieEntry(30F, getString(R.string.active)),
                        PieEntry(20F, getString(R.string.recovered))
                ),
                "key"
        )

        barDataSet.colors = arrayListOf(
                ContextCompat.getColor(requireContext(),R.color.reply_orange_400),
                ContextCompat.getColor(requireContext(),R.color.reply_green_100),
                ContextCompat.getColor(requireContext(),R.color.reply_red_400)
        )
        barDataSet.valueTextColor = Color.WHITE
        barDataSet.valueTextSize = 16F
        val barData = PieData(barDataSet)
        barData.setDrawValues(false)
        with(binding.worldChat) {
            data = barData
            legend.isEnabled = false
            description = null
            holeRadius = PIE_RADIUS
            setDrawEntryLabels(false)
            setHoleColor(ContextCompat.getColor(requireContext(), R.color.background_purple))
            animateY(PIE_ANIMATION_DURATION, Easing.EaseInOutQuart)
            invalidate()
        }
    }

    companion object {
        private const val TEXT_ANIMATION_DURATION = 1000L
        private const val PIE_ANIMATION_DURATION = 1500
        private const val PIE_RADIUS = 75f
    }

}
