package com.skybox.seven.covid.ui.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skybox.seven.covid.R
import com.skybox.seven.covid.adapters.CountriesAdapter
import com.skybox.seven.covid.model.CountriesModel

class CountriesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val arrayList = ArrayList<CountriesModel>()
        val recycler: RecyclerView? = view?.findViewById(R.id.countriesRecycler)

        arrayList.add(CountriesModel("Malawi", "312", "134" ,"12, 302"))
        arrayList.add(CountriesModel("Malawi", "312", "134" ,"12, 302"))
        arrayList.add(CountriesModel("Malawi", "312", "134" ,"12, 302"))
        arrayList.add(CountriesModel("Malawi", "312", "134" ,"12, 302"))
        arrayList.add(CountriesModel("Malawi", "312", "134" ,"12, 302"))


        recycler!!.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = CountriesAdapter(arrayList, this)

    }


}