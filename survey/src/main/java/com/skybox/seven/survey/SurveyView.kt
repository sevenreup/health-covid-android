package com.skybox.seven.survey

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.skybox.seven.survey.config.SurveyConfigs
import com.skybox.seven.survey.databinding.LayoutSurveyViewBinding
import com.skybox.seven.survey.helper.StepAdapter
import com.skybox.seven.survey.model.Step


class SurveyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    private var binding: LayoutSurveyViewBinding = LayoutSurveyViewBinding.inflate(LayoutInflater.from(context), this, true)
    private lateinit var adapter: StepAdapter
    lateinit var viewModel: SurveyViewModel

    init {
        binding.view = this
//        binding.pager.isUserInputEnabled = false
    }

    fun start(steps: List<Step>, configs: SurveyConfigs, surveyViewModel: SurveyViewModel) {
        binding.lifecycleOwner = configs.lifecycleOwner
        viewModel = surveyViewModel
        adapter = StepAdapter(configs.fa, steps)
        binding.pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.previous.value = (position > 0)
            }
        })
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { _, _ ->}.attach()
    }

    fun previousTab() {
        binding.pager.currentItem = binding.pager.currentItem - 1
    }

    fun nextTab() {
        binding.pager.currentItem = binding.pager.currentItem + 1
    }
}
