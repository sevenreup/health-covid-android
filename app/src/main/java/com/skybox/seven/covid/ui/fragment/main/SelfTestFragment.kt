package com.skybox.seven.covid.ui.fragment.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.skybox.seven.covid.R
import java.util.*
import kotlinx.android.synthetic.main.selftest.*

class SelfTestFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.selftest, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        widgetBinders()
        operations()
    }
    private fun widgetBinders() {

        date.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                    activity!!,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                        date.setText("" + dayOfMonth + " " + month + ", " + year)
                    },
                    year,
                    month,
                    day
            )
            dpd.show()
            submission.setOnClickListener {

                progress.visibility = View.GONE
                if (date.text.toString() != "Choose todays date") {


                    progress.visibility = View.VISIBLE
                    submission.visibility = View.GONE
                    Handler().postDelayed({
                        Toast.makeText(activity!!, "Odds for COVID19: 0.07%", Toast.LENGTH_SHORT).show()
                        submission.visibility = View.VISIBLE
                        progress.visibility = View.GONE
                    },5000)
                } else {
                    Toast.makeText(activity!!, "Specify today's date and degree...", Toast.LENGTH_LONG)
                            .show()
                }


            }

        }

    }
    private fun operations(){
        val items = arrayOf("Mild condition","Severe condition","I am not sure")

        spinner.adapter = ArrayAdapter<String>(activity!!,android.R.layout.simple_list_item_1,items)



    }

}