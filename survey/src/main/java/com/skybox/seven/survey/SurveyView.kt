package com.skybox.seven.survey

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.skybox.seven.survey.config.SurveyConfigs
import com.skybox.seven.survey.databinding.LayoutSurveyViewBinding
import com.skybox.seven.survey.helper.StepAdapter
import com.skybox.seven.survey.model.Step


class SurveyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    private var binding: LayoutSurveyViewBinding = LayoutSurveyViewBinding.inflate(LayoutInflater.from(context), this, true)
    private lateinit var adapter: StepAdapter
    private lateinit var viewModel: SurveyViewModel

    init {
//        binding.pager.isUserInputEnabled = false
    }

    fun start(steps: List<Step>, configs: SurveyConfigs, surveyViewModel: SurveyViewModel) {
        viewModel = surveyViewModel
        adapter = StepAdapter(configs.fa, steps)
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { _, _ ->}.attach()
    }
}
