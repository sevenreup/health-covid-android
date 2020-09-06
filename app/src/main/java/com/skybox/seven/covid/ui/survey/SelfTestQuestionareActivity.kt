package com.skybox.seven.covid.ui.survey

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.ActivitySelfTestQuestionareBinding
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.config.SurveyConfigs
import com.skybox.seven.survey.model.BooleanStep
import com.skybox.seven.survey.model.EndStep
import com.skybox.seven.survey.model.MultiSelectionStep
import com.skybox.seven.survey.model.StartStep
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfTestQuestionareActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySelfTestQuestionareBinding
    private val surveyViewModel: SurveyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelfTestQuestionareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val steps = listOf(
                StartStep(
                        getString(R.string.self_test_intro_title),
                        getString(R.string.self_test_intro_sub_title),
                        getString(R.string.start)
                ),
                BooleanStep(
                        getString(R.string.self_test_finish_title),
                        getString(R.string.self_test_finish_sub_title)
                ),
                MultiSelectionStep(
                        getString(R.string.self_test_finish_title),
                        getString(R.string.self_test_finish_sub_title),
                        arrayListOf(
                                "one",
                                "two",
                                "three",
                                "four",
                                "five"
                        )
                ),
                EndStep(
                        getString(R.string.self_test_finish_title),
                        getString(R.string.self_test_finish_sub_title),
                        getString(R.string.finish)
                )
        )
        val config = SurveyConfigs(this, this)
        binding.surveyView.start(steps, config, surveyViewModel)
    }

}