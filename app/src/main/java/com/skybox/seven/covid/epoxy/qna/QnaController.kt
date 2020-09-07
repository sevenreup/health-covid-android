package com.skybox.seven.covid.epoxy.qna

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.Qna

class QnaController : Typed2EpoxyController<Boolean?, List<Qna>>() {

    private var expandedModel = null

    override fun buildModels(loading: Boolean?, questions: List<Qna>) {

        for (question in questions) {
            QnaEpoxyModel_()
                    .id(question.question)
                    .questions(question)
                    .expandListener { model, parentView, clickedView, position ->

                        when (expandedModel) {
                            null -> {
                                expandItem(parentView, true)
                                //expandedModel = parentView
                            }
                            parentView -> {
                                unExpandedItems(parentView, true)
                                expandedModel = null
                            }
                            else -> {
                                expandItem(parentView, false)
                              //  expandItem(expandedModel, true)
                              //  expandedModel = parentView

                            }
                        }

                    }
                    .addTo(this)
        }

    }
    private fun expandItem(holder: QnaEpoxyModel.QnaEpoxyViewHolder,expanded: Boolean){

        holder.answerLayout?.visibility = View.VISIBLE
        holder.arrow?.setImageResource(R.drawable.ic_keyboard_arrow_down)

    }

    private fun unExpandedItems(holder: QnaEpoxyModel.QnaEpoxyViewHolder, unExpanded: Boolean){

        holder.answerLayout?.visibility = View.GONE
        holder.arrow?.setImageResource(R.drawable.ic_keyboard_arrow)

    }

}

