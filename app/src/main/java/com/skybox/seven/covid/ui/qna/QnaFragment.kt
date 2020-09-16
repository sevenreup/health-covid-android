package com.skybox.seven.covid.ui.qna

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skybox.seven.covid.databinding.FragmentQnaBinding
import com.skybox.seven.covid.epoxy.qna.QnaController


class QnaFragment: Fragment() {

    private lateinit var binding: FragmentQnaBinding
    private val viewModel: QnAViewModel by viewModels()
    val controller = QnaController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.qna.observe(this, Observer {
            controller.setData(false, it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentQnaBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.qnaRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setController(controller)
        }
        return binding.root
    }

    fun onBackPressed(view: View) {
        findNavController().navigateUp()
    }


}
