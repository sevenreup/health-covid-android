package com.skybox.seven.covid.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R

class TabPagerAdapter(private val context: Context): RecyclerView.Adapter<TabsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabsViewHolder =
            TabsViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_tab_indicator, parent, false))

    override fun onBindViewHolder(holder: TabsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 3

}

class TabsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var view: View = itemView
}