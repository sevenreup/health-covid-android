package com.skybox.seven.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.checkbox.MaterialCheckBox
import com.skybox.seven.survey.databinding.FragmentMultiSelectionBinding
import com.skybox.seven.survey.helper.QuestionFragmentArgs

private const val LIST_OFF_QUESTIONS = "question_list"

class MultiSelectionFragment : Fragment() {
    private lateinit var binding: FragmentMultiSelectionBinding
    lateinit var args: QuestionFragmentArgs
    var answers: ArrayList<String>? = null
    private val checkBoxes: ArrayList<MaterialCheckBox> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = QuestionFragmentArgs(requireArguments())
        answers = requireArguments().getStringArrayList(LIST_OFF_QUESTIONS)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMultiSelectionBinding.inflate(inflater, container, false)

        answers?.forEach {
            val check = MaterialCheckBox(context).apply {
                text = it
            }
            binding.holder.addView(check)
            checkBoxes.add(check)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String, subTitle: String, questions: ArrayList<String>) =
                MultiSelectionFragment().apply {
                    arguments = QuestionFragmentArgs.putGenerics(title, subTitle).apply {
                        putStringArrayList(LIST_OFF_QUESTIONS, questions)
                    }
                }
    }
}