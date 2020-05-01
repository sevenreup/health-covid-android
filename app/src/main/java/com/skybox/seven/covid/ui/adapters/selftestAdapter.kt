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

class selftestAdapter(val arrayList: ArrayList<dbModel>, val context: SelfTestFragment): RecyclerView.Adapter<selftestAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
          val response = itemView.response
          val status = itemView.actual_status

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
    }


}




