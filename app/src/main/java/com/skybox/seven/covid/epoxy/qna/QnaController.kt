package com.skybox.seven.covid.epoxy.qna

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.alpha
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.Qna

class QnaController : Typed2EpoxyController<Boolean?, List<Qna>>() {

    private var expandedModel: QnaEpoxyModel.QnaEpoxyViewHolder? = null

    override fun buildModels(loading: Boolean?, questions: List<Qna>) {

        for (question in questions) {
            QnaEpoxyModel_()
                    .id(question.question)
                    .questions(question)
                    .expandListener { _, parentView, _, _ ->
                        expandedModel = when {
                            expandedModel == null -> {
                                expandItem(parentView, false)
                                parentView
                            }
                            expandedModel === parentView -> {
                                expandItem(parentView, true)
                                null
                            }
                            else -> {
                                expandItem(expandedModel!!, true)
                                expandItem(parentView, false)
                                parentView
                            }
                        }
                    }
                    .addTo(this)
        }

    }

    private fun expandItem(holder: QnaEpoxyModel.QnaEpoxyViewHolder, expanded: Boolean) {

        if (expanded) {
            holder.answer.apply {
                animate()
                        .alpha(0f)
                        .setDuration(context.resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                holder.answer.visibility = View.GONE
                            }
                        })
            }
            holder.arrow.setImageResource(R.drawable.ic_keyboard_arrow)
        } else {
            holder.answer.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate().alpha(1f)
                        .setDuration(context.resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
                        .setListener(null)
            }

            holder.arrow.setImageResource(R.drawable.ic_keyboard_arrow_down)
        }
    }

}

