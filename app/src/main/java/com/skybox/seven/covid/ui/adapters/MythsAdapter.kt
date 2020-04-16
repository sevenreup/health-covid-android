package com.skybox.seven.covid.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.ui.adapters.MythsModel
import com.skybox.seven.covid.ui.fragment.main.MythBusterFragment
import kotlinx.android.synthetic.main.list_myths.view.*

class MythsAdapter(val arrayList: ArrayList<MythsModel>, val context: MythBusterFragment): RecyclerView.Adapter<MythsAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(model: MythsModel){

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

        }
    }


}