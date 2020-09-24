package com.skybox.seven.covid.ui.survey

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.SelfTestAnswer
import com.skybox.seven.covid.databinding.ActivitySelfTestQuestionareBinding
import com.skybox.seven.covid.util.Constants
import com.skybox.seven.survey.SurveyView
import com.skybox.seven.survey.answer.BooleanResult
import com.skybox.seven.survey.answer.MultiChoiceResult
import com.skybox.seven.survey.answer.Result
import com.skybox.seven.survey.answer.TextResult
import com.skybox.seven.survey.config.UtilityText
import com.skybox.seven.survey.helper.SurveyCallbacks
import com.skybox.seven.survey.model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfTestQuestionnaireActivity: AppCompatActivity(), SurveyCallbacks {
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
                            MultiSelectionStep(quest.questionID,
                                    quest.question ?: "",
                                    quest.subTitle ?: "",
                                    quest.arrays ?: ArrayList()
                            )
                    )
                    Constants.TEXT -> steps.add(
                            TextStep(
                                    quest.questionID,
                                    quest.question ?: "",
                                    quest.subTitle ?: "",
                                    true,
                                    getString(R.string.text_survey_hint)
                            )
                    )
                    Constants.ARRAYBOOLEAN -> steps.add(
                            ArrayBooleanStep(quest.questionID,
                                    quest.question ?: "",
                                    quest.subTitle ?: "",
                                    quest.arrays ?: ArrayList())
                    )
                    else ->
                        steps.add(BooleanStep(quest.questionID,
                                quest.question ?: "",
                                quest.subTitle ?: ""))
                }
            }
            steps.add(
                    EndStep(
                            title = getString(R.string.self_test_finish_title),
                            subtitle = getString(R.string.self_test_finish_sub_title),
                            buttonText = getString(R.string.finish)
                    )
            )

            val config = UtilityText( getString(R.string.next), getString(R.string.cancel), getString(R.string.yes), getString(R.string.no))
            val fragment = SurveyView.newInstance(steps, config)
            supportFragmentManager.beginTransaction().add(R.id.survey_view, fragment).commit()
        })

        viewModel.finished.observe(this, Observer {
            finish()
        })
    }

    override fun finished(answers: HashMap<Int, Result>?) {
        val answerList: MutableList<SelfTestAnswer> = ArrayList()
        answers?.forEach {
            when(it.value.type) {
                Result.MULTI ->
                {
                    val answer = it.value as MultiChoiceResult
                    val selections = answer.answers.keys.toList()
                    answerList += SelfTestAnswer(
                            null,
                            Constants.ARRAY,
                            0,
                            answer.id,
                            selections,
                            null,
                            null,
                            answer.endDate)
                }
                Result.TEXT -> {
                    val answer = it.value as TextResult
                    answerList += SelfTestAnswer(
                            null,
                            Constants.BOOLEAN,
                            0,
                            answer.id,
                            null,
                            null,
                            answer.text,
                            answer.endDate
                    )
                }
                else -> {
                    val answer = it.value as BooleanResult
                    answerList += SelfTestAnswer(
                            null,
                            Constants.BOOLEAN,
                            0,
                            answer.id,
                            null,
                            answer.answer,
                            null,
                            answer.endDate
                    )
                }
            }
        }
        viewModel.submit(answers, getString(R.string.self))
    }

    override fun surveyClosed() {
        MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.cancel_survey))
                .setNeutralButton(getString(R.string.no)) { _, _ -> }
                .setPositiveButton(getString(R.string.yes)) {_, _ -> finish()}
                .show()
    }

    override fun onBackPressed() {
        surveyClosed()
    }

}