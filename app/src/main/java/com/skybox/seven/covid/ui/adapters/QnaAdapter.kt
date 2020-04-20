package com.skybox.seven.covid.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

                }
                1 -> {
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_two)
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
                    display.setText(R.string.question_three)
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
                    display.setText (R.string.question_four)
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
                    display.setText(R.string.question_five)
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
                    display.setText(R.string.question_six)
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
                    display.setText(R.string.question_seven)
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
                    display.setText(R.string.question_eight)
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
                    display.setText(R.string.question_nine)
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
                    display.setText(R.string.question_ten)
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
                    display.setText(R.string.question_eleven)
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
                    display.setText(R.string.question_twelve)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                12 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText (R.string.question_thirteen)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                13 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_fourteen)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                14 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_fifteen)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                15 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_sixteen)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                16 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_seventeen)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                17 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_eighteen)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                18 -> {
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_nineteen)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                19 -> {
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_twenty)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                20 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_twenty_one)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                21 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_twenty_two)
                    dialog.setContentView(view)
                    dialog.show()
                    close.setOnClickListener{
                        dialog.dismiss()
                    }
                }
                22 ->{
                    val dialog = BottomSheetDialog(context.activity!!)
                    val view = dialog.layoutInflater.inflate(R.layout.question_one, null)
                    val close = view.findViewById<TextView>(R.id.close)
                    val display = view.findViewById<TextView>(R.id.displayer_)
                    display.setText(R.string.question_twenty_three)
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