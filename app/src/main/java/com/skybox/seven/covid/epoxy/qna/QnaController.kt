package com.skybox.seven.covid.epoxy.qna

import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.data.entities.Qna


class QnaController : Typed2EpoxyController<Boolean?, List<Qna>>() {

    private val callback: dataCallback? = null

    override fun buildModels(loading: Boolean?, questions: List<Qna>) {

        for (question in questions) {
            QnaEpoxyModel_()
                    .id(question.question)
                    .questions(question)
                    .expandListener { model, parentView, clickedView, position ->
                        callback?.onExpand(position)
                    }
                    .addTo(this)

        }

    }

    interface dataCallback {
        fun onExpand(pos: Int)
    }


}

