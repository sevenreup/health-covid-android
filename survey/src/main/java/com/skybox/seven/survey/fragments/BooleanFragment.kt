package com.skybox.seven.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skybox.seven.survey.databinding.FragmentBooleanBinding
import com.skybox.seven.survey.helper.QuestionFragmentArgs
import com.skybox.seven.survey.views.createRadioButton


class BooleanFragment : Fragment() {
    lateinit var args: QuestionFragmentArgs
    lateinit var binding: FragmentBooleanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = QuestionFragmentArgs(requireArguments())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBooleanBinding.inflate(inflater, container, false)
        binding.radioGroup.apply {
            removeAllViews()
            addView(createRadioButton("yes", "yes", context))
            addView(createRadioButton("no", "no", context))
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String, subTitle: String) = BooleanFragment().apply {
            arguments = QuestionFragmentArgs.putGenerics(title, subTitle)
        }
    }
}