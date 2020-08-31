package com.skybox.seven.covid.ui.qna

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.Qna
import com.skybox.seven.covid.epoxy.qna.QnaController
import com.skybox.seven.covid.util.SpaceItemDecorator

class QnaFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_qna, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recycler = requireView().findViewById<EpoxyRecyclerView>(R.id.qnaRecycler)
        val controller = QnaController()

        val arrayList = ArrayList<Qna>()

        arrayList.add(Qna("Hi", "dnd"))

        controller.setData(true, arrayList)

        recycler.addItemDecoration(SpaceItemDecorator(20, true, false))
        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setController(controller)
        }

    }


}
