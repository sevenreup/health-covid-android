package com.skybox.seven.covid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.model.CountriesModel
import com.skybox.seven.covid.ui.stats.countriesStats.CountriesFragment
import kotlinx.android.synthetic.main.countries_recycler.view.*

class CountriesAdapter(val arrayList: ArrayList<CountriesModel>, val context: CountriesFragment): RecyclerView.Adapter<CountriesAdapter.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(model: CountriesModel){

            itemView.total_recovered_no.text = model.recovered
            itemView.total_active_no.text = model.active
            itemView.total_deaths_no.text = model.active
            itemView.country.text = model.country
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.countries_recycler,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

    }
    }



