package com.skybox.seven.covid.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController

import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.dbModel
import kotlinx.android.synthetic.main.fragment_actul_test_.*


class actulTest_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_actul_test_, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        submit.setOnClickListener{

            if(getResponse.text.isEmpty()){
                Toast.makeText(this.context!!, "Please enter respose", Toast.LENGTH_LONG)
                        .show()
                getResponse.requestFocus()
            }else{
                val self = dbModel()
                self.Reply = getResponse.text.toString()
                if(getStatus.text.isEmpty()){
                    self.Status = "Declined"
                }else{
                    self.Status = getStatus.text.toString()
                    SelfTestFragment.dbHandler2.addTest(this.context!!, self)
                    getResponse.text.clear()
                    getStatus.text.clear()
                    getResponse.requestFocus()
                }
            }

        }
        cancel.setOnClickListener {

            getResponse.text.clear()
            getStatus.text.clear()
            findNavController(it).navigate(R.id.action_actulTest_Fragment_to_selfTestFragment)

        }
    }

}
