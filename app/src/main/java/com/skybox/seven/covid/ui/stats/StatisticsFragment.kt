package com.skybox.seven.covid.ui.stats

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentStatsBinding
import com.skybox.seven.covid.model.WorldStats
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatsBinding
    val viewModel: StatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMalawiData()
        viewModel.getWorldData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        viewModel.worldData.observe(viewLifecycleOwner, Observer { worldChat(it) })
        return binding.root
    }

    fun toMWStats() {
        findNavController().navigate(R.id.action_statsFragment_to_statsBarChatFragment)
    }

    fun toAllCountries() {
        findNavController().navigate(R.id.action_statsFragment_to_countriesFragment)
    }

    private fun worldChat(wordData: WorldStats) {
        val barDataSet = PieDataSet(
                listOf(
                        PieEntry(wordData.deaths.toFloat(), getString(R.string.deaths)),
                        PieEntry(wordData.active.toFloat(), getString(R.string.active)),
                        PieEntry(wordData.recovered.toFloat(), getString(R.string.recovered))
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
        private const val PIE_ANIMATION_DURATION = 1500
        private const val PIE_RADIUS = 75f
    }

}
