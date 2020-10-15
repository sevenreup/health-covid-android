package com.skybox.seven.covid.epoxy.qna

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.card.MaterialCardView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.Qna
import com.skybox.seven.covid.helpers.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.model_qna)
abstract class QnaEpoxyModel : EpoxyModelWithHolder<QnaEpoxyModel.QnaEpoxyViewHolder>() {
    @EpoxyAttribute
    var questions: Qna? = null

    @EpoxyAttribute
    var expandListener: View.OnClickListener? = null

    override fun createNewHolder(): QnaEpoxyViewHolder {
        return QnaEpoxyViewHolder()
    }

    override fun bind(holder: QnaEpoxyViewHolder) {
        super.bind(holder)
        holder.question.text = questions!!.question
        holder.answer.text = questions!!.answer

        holder.getView().setOnClickListener(expandListener)
    }

    override fun getDefaultLayout(): Int {
        return R.layout.model_qna
    }

    class QnaEpoxyViewHolder : KotlinEpoxyHolder() {
        val question by bind<TextView>(R.id.qnaQuestion)
        val answer by bind<TextView>(R.id.qnaAnswer)
        val arrow by bind<ImageView>(R.id.qnaArrow)
        val layout by bind<ConstraintLayout>(R.id.answerLayout)
    }
}