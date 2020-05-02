package com.skybox.seven.covid.ui.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.dbModel
import com.skybox.seven.covid.model.SelfTestModel
import com.skybox.seven.covid.ui.fragment.main.SelfTestFragment
import kotlinx.android.synthetic.main.selftestrecycler_layout.view.*
import java.util.*
import kotlin.collections.ArrayList


class selftestAdapter(val arrayList: ArrayList<dbModel>, val context: SelfTestFragment): RecyclerView.Adapter<selftestAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
          val response = itemView.response
          val status = itemView.actual_status

          val date = itemView.test_date
          val time = itemView.test_time


          val modifications = itemView.menu_more
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): selftestAdapter.ViewHolder {
           val v = LayoutInflater.from(p0.context).inflate(R.layout.selftestrecycler_layout, p0, false)
           return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(p0: selftestAdapter.ViewHolder, p1: Int) {
        val values: dbModel = arrayList[p1]
        p0.response.text = values.Reply
        p0.status.text = values.Status

        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH).toString()
        val month = c.get(Calendar.MONTH).toString()
        val year = c.get(Calendar.YEAR).toString()

        val hour = c.get(Calendar.HOUR_OF_DAY)

        when(hour){
            in 0..11 ->{
                val hours = hour.toString()
                val minutes = c.get(Calendar.MINUTE).toString()

                p0.time.text = "$hours:$minutes AM"
                p0.date.text = "$day/$month/$year"
            }
            in 12..23 ->{
                val hours = hour.toString()
                val minutes = c.get(Calendar.MINUTE).toString()

                p0.time.text = "$hours:$minutes PM"
                p0.date.text = "$day/$month/$year"
            }
        }



        p0.modifications.setOnClickListener {
            val popUp = PopupMenu(this.context.activity, it)
            popUp.inflate(R.menu.menu_more)
            popUp.setOnMenuItemClickListener {item ->
                when (item.itemId) {
                    R.id.del -> {
                        if (SelfTestFragment.dbHandler2.deleteTest(values.ColumnId)){
                            arrayList.removeAt(p1)
                            notifyItemRemoved(p1)
                            notifyItemRangeChanged(p1, arrayList.size)
                        }
                        true
                    }else -> false
                }
            }
            popUp.show()

        }


    }


}




