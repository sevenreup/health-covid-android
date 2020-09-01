package com.skybox.seven.covid.ui.qna

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.skybox.seven.covid.databinding.FragmentQnaBinding
import com.skybox.seven.covid.epoxy.qna.QnaController


class QnaFragment: Fragment() {

    private lateinit var binding: FragmentQnaBinding

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(QnaViewModel::class.java)
    }
    private val controller = QnaController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentQnaBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerHandler()
    }

    private fun recyclerHandler(){

        val recycler = binding.qnaRecycler
        recycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = controller.adapter
        }

        viewModel.qnaLiveData.observe(viewLifecycleOwner, Observer { container ->
            controller.setData(container)
        })

    }

    fun onBackPressed(view: View) {
        requireActivity().onBackPressed()
    }


}
