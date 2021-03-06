package com.skybox.seven.covid.ui.stats.overview

import android.content.ClipData
import android.content.Context
import android.content.Intent
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
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.skybox.seven.covid.R
import com.skybox.seven.covid.StatsDirections
import com.skybox.seven.covid.databinding.FragmentStatsBinding
import com.skybox.seven.covid.databinding.LayoutTotalCasesBinding
import com.skybox.seven.covid.helpers.DateHelper
import com.skybox.seven.covid.helpers.TextFormatter.formatNumber
import com.skybox.seven.covid.model.WorldStats
import com.skybox.seven.covid.util.getLocalBitmapUri
import com.skybox.seven.covid.util.toImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatsBinding
    val viewModel: StatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMainMalawiData()
        viewModel.getWorldData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this

        binding.errorHolder.onclick = View.OnClickListener {
            binding.refresh.isRefreshing = true
            viewModel.getWorldData()
            viewModel.getMainMalawiData()
        }

        viewModel.worldData.observe(viewLifecycleOwner, Observer {
            binding.worldContainer.apply {
                title = getString(R.string.cases_worldwide)
                cases = it.cases.formatNumber()
                active = it.active.formatNumber()
                deaths = it.deaths.formatNumber()
                recovered = it.recovered.formatNumber()
                date = DateHelper.formatDate(it.updated.toString())
                problems.visibility = View.VISIBLE
                share.visibility = View.VISIBLE
                share.setOnClickListener {
                    shareShoot(this, requireContext())
                }
            }
            worldChat(it)
            binding.refresh.isRefreshing = false
        })

        binding.refresh.setOnRefreshListener {
            viewModel.getMainMalawiData()
            viewModel.getWorldData()
        }

        viewModel.networkError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.refresh.isRefreshing = false
            }
        })
        return binding.root
    }

    fun toMWStats() {
        if (viewModel.malawiData.value != null) {
            val action = StatsDirections.toCountryStatsFragment(viewModel.malawiData.value!!)
            findNavController().navigate(action)
        }
        // todo: fix this weird null nav state
    }

    private fun worldChat(wordData: WorldStats) {
        val barDataSet = PieDataSet(
                listOf(
                        PieEntry(wordData.deaths.toFloat(), requireContext().getString(R.string.deaths)),
                        PieEntry(wordData.active.toFloat(), requireContext().getString(R.string.active)),
                        PieEntry(wordData.recovered.toFloat(), requireContext().getString(R.string.recovered))
                ),
                "key"
        )
        setUpBar(barDataSet, requireContext(), binding.worldContainer.worldChat)
    }


    companion object {
        private const val PIE_ANIMATION_DURATION = 1500
        private const val PIE_RADIUS = 40f

        fun setUpBar(barDataSet: PieDataSet, context: Context, pieChart: PieChart): PieData {
            barDataSet.colors = arrayListOf(
                    ContextCompat.getColor(context, R.color.deaths),
                    ContextCompat.getColor(context, R.color.active),
                    ContextCompat.getColor(context, R.color.recovered)
            )
            barDataSet.valueTextColor = Color.WHITE
            barDataSet.valueTextSize = 16F
            val barData = PieData(barDataSet)
            barData.setDrawValues(false)


            with(pieChart) {
                data = barData
                legend.isEnabled = false
                description = null
                holeRadius = PIE_RADIUS
                setDrawEntryLabels(false)
                setHoleColor(ContextCompat.getColor(context, R.color.color_background))
                animateY(PIE_ANIMATION_DURATION, Easing.EaseInOutQuart)
                invalidate()
            }

            return barData
        }

        fun shareShoot(binding: LayoutTotalCasesBinding, context: Context?) {
            binding.share.visibility = View.GONE

            val image = binding.root.toImage()
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "image/jpeg"
                if (image != null) {
                    val uri = context?.let { image.getLocalBitmapUri(it) }
                    putExtra(Intent.EXTRA_STREAM, uri)
                    clipData = ClipData.newUri(context?.contentResolver, context?.getString(R.string.app_name), uri)
                }
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context?.startActivity(Intent.createChooser(intent, null))

            binding.share.visibility = View.VISIBLE
        }

        fun formatNumber(int: Int?) = int.formatNumber()

        fun formatDate(string: String?) = DateHelper.formatDate(string)
    }

}
