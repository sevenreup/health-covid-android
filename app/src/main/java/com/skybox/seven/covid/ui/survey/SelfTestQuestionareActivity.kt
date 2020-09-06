package com.skybox.seven.covid.ui.survey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.ActivitySelfTestQuestionareBinding
import com.skybox.seven.survey.config.SurveyConfigs
import com.skybox.seven.survey.model.EndStep
import com.skybox.seven.survey.model.StartStep
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfTestQuestionareActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySelfTestQuestionareBinding

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
                EndStep(
                        getString(R.string.self_test_finish_title),
                        getString(R.string.self_test_finish_sub_title),
                        getString(R.string.finish)
                )
        )
        val config = SurveyConfigs(this)
        binding.surveyView.start(steps, config)
    }

}