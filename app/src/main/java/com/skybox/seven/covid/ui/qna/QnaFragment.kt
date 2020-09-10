package com.skybox.seven.covid.ui.qna

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.Qna
import com.skybox.seven.covid.data.entities.getQnaData
import com.skybox.seven.covid.databinding.FragmentQnaBinding
import com.skybox.seven.covid.epoxy.qna.QnaController


class QnaFragment: Fragment() {

    private lateinit var binding: FragmentQnaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentQnaBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val data = getQnaData()
        recyclerHandler(data as MutableList<Qna>)


    }

    private fun recyclerHandler(data: MutableList<Qna>){

        val recycler = binding.qnaRecycler
        val controller = QnaController()

        controller.setData(false, data)

        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setController(controller)
        }


    }


    fun onBackPressed(view: View) {
        findNavController().navigateUp()
    }


}
