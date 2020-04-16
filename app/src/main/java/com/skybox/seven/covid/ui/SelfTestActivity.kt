package com.skybox.seven.covid.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import android.widget.Button
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.skydoves.powerspinner.SpinnerAnimation
import com.skydoves.powerspinner.SpinnerGravity
import com.skydoves.powerspinner.createPowerSpinnerView
import kotlinx.android.synthetic.main.selftest.*
import java.util.*


class SelfTestActivity : AppCompatActivity() {

    val types = arrayOf("Mild condition", "High severity condition", "I am not quite sure")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selftest)


        widgetBinders()
    }


    private fun widgetBinders() {


        val date: Button = findViewById(R.id.date)
        date.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    date.setText("" + dayOfMonth + " " + month + ", " + year)
                },
                year,
                month,
                day
            )
            dpd.show()
            submission.setOnClickListener {
                val progress: ProgressBar = findViewById(R.id.progress)
                progress.visibility = View.GONE
                if (date.text.toString() != "Choose todays date") {
                    val dialog = Dialog(this)
                    val view = layoutInflater.inflate(R.layout.message_fragment, null)
                    dialog.setContentView(view)
                    dialog.window?.setBackgroundDrawable(ColorDrawable(TRANSPARENT))

                    val inserter: TextView = view.findViewById(R.id.results)
                    inserter.text = "Odds for COVID19 = 40%"

                    progress.visibility = View.VISIBLE
                    submission.visibility = View.GONE
                    Handler().postDelayed({
                        dialog.show()
                        submission.visibility = View.VISIBLE
                        progress.visibility = View.GONE
                    },5000)
                } else {
                    Toast.makeText(this, "Specify today's date and degree...", Toast.LENGTH_LONG)
                        .show()
                }


            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater

        inflater.inflate(R.menu.menu, menu)
        val manager:SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searcher = menu?.findItem(R.id.search)
        val searchview = searcher?.actionView as SearchView

        searchview.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchview.clearFocus()
                searchview.setQuery("", false)
                Toast.makeText(this@SelfTestActivity, "Explanation for $query", Toast.LENGTH_LONG).show()
                searcher.collapseActionView()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    return true
    }
}
