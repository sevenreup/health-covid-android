package com.skybox.seven.covid.ui.survey

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.ActivitySelfTestQuestionareBinding
import com.skybox.seven.covid.util.Constants
import com.skybox.seven.survey.SurveyView
import com.skybox.seven.survey.SurveyViewModel
import com.skybox.seven.survey.answer.Result
import com.skybox.seven.survey.config.SurveyConfigs
import com.skybox.seven.survey.config.UtilityText
import com.skybox.seven.survey.helper.SurveyCallbacks
import com.skybox.seven.survey.model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfTestQuestionareActivity: AppCompatActivity(), SurveyCallbacks {
    private lateinit var binding: ActivitySelfTestQuestionareBinding
    private val viewModel: SelfTestQuestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelfTestQuestionareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.questions.observe(this, Observer {
            val steps = arrayListOf<Step>(
                    StartStep(
                            title = getString(R.string.self_test_intro_title),
                            subTile = getString(R.string.self_test_intro_sub_title),
                            buttonText = getString(R.string.start)
                    )
            )
            Log.e("TAG", "onCreate: ${it.size}")
            it.forEach { quest ->
                when (quest.type) {
                    Constants.ARRAY -> steps.add(
                            MultiSelectionStep(quest.id,
                                    quest.question,
                                    quest.subTitle,
                                    quest.arrays!!
                            )
                    )
                    else ->
                        steps.add(BooleanStep(quest.id,
                                quest.question,
                                quest.subTitle))
                }
            }
            steps.add(
                    EndStep(
                            title = getString(R.string.self_test_finish_title),
                            subtitle = getString(R.string.self_test_finish_sub_title),
                            buttonText = getString(R.string.finish)
                    )
            )

            val config = UtilityText("Next", "Cancel")
            val fragment = SurveyView.newInstance(steps, config)
            supportFragmentManager.beginTransaction().add(R.id.survey_view, fragment).commit()
        })

    }

    override fun finished(answers: HashMap<Int, Result>?) {
    }

}