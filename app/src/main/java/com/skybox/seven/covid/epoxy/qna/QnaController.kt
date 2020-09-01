package com.skybox.seven.covid.epoxy.qna

import com.airbnb.epoxy.TypedEpoxyController
import com.skybox.seven.covid.epoxy.main.header
import com.skybox.seven.covid.model.Answer
import com.skybox.seven.covid.model.Container
import com.skybox.seven.covid.model.Question
import com.skybox.seven.covid.databinding.ModelQnaHeaderBindingImpl


class QnaController : TypedEpoxyController<Container>() {
//    private lateinit var binding: ModelQnaHeaderBindingImpl
//this works too but l failed to implement expansion the views using this
    //override fun buildModels(loading: Boolean?, questions: List<Qna>) {
    //  for (question in questions) {
    //    QnaEpoxyModel_().id(question.question).questions(question).addTo(this)
    //}
    //}
    override fun buildModels(container: Container?) {
        container?.questions?.forEach {
            header {
                id(it.question.id)
                Question(it.question.name)
//                binding.setOnHeaderExpanded { QnaModel ->
//                    container.questions
//                }
            }
            if (it.question.isExpanded) {
                it.answers.forEach() {

                    Answer(it.name)

                }
            }
        }
    }
    /**
     * have a grobal var to hold last expanded
     * var expanded model = null
     *
     * QnaEpoxyModel_()..clickListener((model, parentView, clickedView, position) -> {
     *  if (expandedModel == null) {
     *      expandItem(parentView, true);
     *      expandedModel = parentView;
     *      }
     *  else if (expandedModel == parentView) {
     *      expandItem(parentView, false);
     *      expandedModel = null;
     *      }
     *  else {
     *      expandItem(expandedModel, false);
     *      expandItem(parentView, true);
     *      expandedModel = parentView;
     *      }
     * })
     *
     * the boolean will toggle
     * the holder lets you access the views
     * private void expandItem(QnaEpoxyModel.QnaEpoxyViewHolder holder, Boolean expanded) {
     *      holder.question
     * }
     * */
}

