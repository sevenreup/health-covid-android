package com.skybox.seven.covid.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.skybox.seven.covid.R
import com.skybox.seven.covid.ui.fragment.main.QnAFragment
import kotlinx.android.synthetic.main.list_myths.view.*

class QnaAdapter(val arrayList: ArrayList<QnaModel>, val context: QnAFragment): RecyclerView.Adapter<QnaAdapter.ViewHolder>(){

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

        holder.itemView.setOnClickListener {
            when(position){
                0 -> {
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                    Toast.makeText(context.activity, "What is COVID19?", Toast.LENGTH_LONG ).show()
                }
                1 -> {
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_two
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                2 -> {
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_three
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                3 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_four
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                4 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_five
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                5 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_six
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                6 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_seven
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                7 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_eight
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                8 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_nine
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                9 -> {
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_ten
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                10 -> {
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_eleven
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                11 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    val Text = R.string.question_twelve
                    display.text = Text.toString()
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
            }

        }
    }



}