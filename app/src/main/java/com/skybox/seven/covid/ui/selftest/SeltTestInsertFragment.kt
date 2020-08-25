package com.skybox.seven.covid.ui.selftest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.SelfTestResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_actul_test_.*


@AndroidEntryPoint
class SeltTestInsertFragment : Fragment() {
    private val viewModel: SelfTestViewModel by viewModels()

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
                val self = SelfTestResult()
                self.reply = getResponse.text.toString()
                if(getStatus.text.isEmpty()){
                    self.status = "Declined"
                }else{
                    self.status = getStatus.text.toString()
                    viewModel.addTest(self)
                    getResponse.text.clear()
                    getStatus.text.clear()
                    getResponse.requestFocus()
                }
            }

        }
        cancel.setOnClickListener {

            getResponse.text.clear()
            getStatus.text.clear()
//            findNavController(it).navigate(R.id.action_actulTest_Fragment_to_selfTestFragment)

        }
    }

}
