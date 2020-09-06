package com.skybox.seven.survey.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skybox.seven.survey.databinding.FragmentEndStepBinding
import com.skybox.seven.survey.helper.BasicFragmentArgs


private const val BUTTON_TITLE = "button_title"

class EndStepFragment : Fragment() {
    private lateinit var binding: FragmentEndStepBinding
    private lateinit var args: BasicFragmentArgs
    private var buttonTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = BasicFragmentArgs(requireArguments())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentEndStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String, subTitle: String, buttonText: String) =
                EndStepFragment().apply {
                    arguments = BasicFragmentArgs.putGenerics(title, subTitle)
                }
    }
}