package com.skybox.seven.covid.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R

private const val BAR = 0
private const val LINE = 1
class StatsPagerAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == BAR) {
            BarViewHolder(inflater.inflate(R.layout.layout_barchart, parent, false))
        } else {
            LineViewHolder(inflater.inflate(R.layout.layout_linegraph, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 2

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            BAR
        } else {
            LINE
        }
    }
}

class BarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    
}

class LineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}