package com.skybox.seven.covid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.skybox.seven.covid.R
import com.skybox.seven.covid.ui.qna.QnaFragment
import kotlinx.android.synthetic.main.model_qna.view.*

class QnaAdapter(val arrayList: ArrayList<QnaModel>, val context: QnaFragment): RecyclerView.Adapter<QnaAdapter.ViewHolder>(){

    private var dialog = BottomSheetDialog(context.activity!!)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(model: QnaModel){

            itemView.qnaQuestion.text = model.question
            itemView.qnaAnswer.text = model.answer
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.model_qna,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener {

            holder.itemView.answerLayout.visibility = View.VISIBLE
            holder.itemView.qnaArrow.setImageResource(R.drawable.ic_keyboard_arrow_down)

            }

        }
    }



