package com.skybox.seven.covid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.skybox.seven.covid.R
import com.skybox.seven.covid.ui.qna.QnAFragment
import kotlinx.android.synthetic.main.list_myths.view.*

class QnaAdapter(val arrayList: ArrayList<QnaModel>, val context: QnAFragment): RecyclerView.Adapter<QnaAdapter.ViewHolder>(){

    private var dialog = BottomSheetDialog(context.activity!!)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(model: QnaModel){

            itemView.id_.text = model.title
            itemView.tag_.text = model.desc
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_myths,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        val res = this.context.resources
        val items = res.getStringArray(R.array.answers)

        holder.itemView.setOnClickListener {

                val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                val close = view.findViewById<TextView>(R.id.close)
                dialog.setContentView(view)
                val display = view.findViewById<TextView>(R.id.displayer_)
                display.setText(items[position])
                dialog.show()
                close.setOnClickListener{
                    dialog.dismiss()
                }




            }

        }
    }



