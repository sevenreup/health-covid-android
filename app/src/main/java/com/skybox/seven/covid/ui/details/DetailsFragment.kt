package com.skybox.seven.covid.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.DetailsPhoneNumbers
import com.skybox.seven.covid.data.entities.getDetailsData
import com.skybox.seven.covid.databinding.FragmentDetailsBinding
import com.skybox.seven.covid.epoxy.details.DetailsController
import com.skybox.seven.covid.util.SpaceItemDecorator


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding =  FragmentDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        super.onCreate(savedInstanceState)

        val data = getDetailsData()
        bindEpoxyRecycler(data as MutableList<DetailsPhoneNumbers>)
    }


    private fun bindEpoxyRecycler(data: MutableList<DetailsPhoneNumbers>){

        val recycler = binding.detailsRecycler
        val controller = DetailsController()

        controller.setData(false, data)

        recycler.apply {

            addItemDecoration(SpaceItemDecorator(20, true, false))
            setController(controller)
        }

    }

    fun toHomePage(view: View){
        findNavController().navigate(R.id.action_detailsFragment_to_homePageFragment)
    }

}