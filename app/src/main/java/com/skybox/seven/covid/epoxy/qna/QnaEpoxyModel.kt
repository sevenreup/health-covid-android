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
        holder.question!!.text = questions!!.question
        holder.answer!!.text = questions!!.answer

        holder.card?.setOnClickListener(expandListener)


    }

    override fun getDefaultLayout(): Int {
        return R.layout.model_qna
    }

    class QnaEpoxyViewHolder : EpoxyHolder() {

        var question: TextView? = null
        var answer: TextView? = null
        var card: MaterialCardView? = null
        var arrow: ImageView? = null
        var answerLayout: ConstraintLayout? = null


        override fun bindView(itemView: View) {
            question = itemView.findViewById(R.id.qnaQuestion)
            answer = itemView.findViewById(R.id.qnaAnswer)
            card = itemView.findViewById(R.id.qnaModel)
            arrow = itemView.findViewById(R.id.qnaArrow)
            answerLayout = itemView.findViewById(R.id.answerLayout)
        }

    }
}