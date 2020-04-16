package app.covid19

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    val types = arrayOf("Mild condition","High severity condition","I am not quite sure")
    lateinit var submission: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selftest_main)


       widgetBinders()
    }



    private fun widgetBinders(){

        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this,
                        types[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
        val date: Button = findViewById(R.id.date)
        date.setOnClickListener{

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                date.setText("" + dayOfMonth + " " + month + ", " + year)
            }, year, month, day)
            dpd.show()

        }
        scrollview.getViewTreeObserver()
            .addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
                if (scrollview.getChildAt(0).getBottom() <= scrollview.getHeight() + scrollview.getScrollY()) {
                    Toast.makeText(this, "Scroll down for other part", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scroll up for other part", Toast.LENGTH_LONG).show()
                }
            })
        submissionListener()

    }

    private fun submissionListener(){

        submission = findViewById(R.id.submission)

        submission.setOnClickListener {

            if(date.text.toString() != "Choose todays date" ){
                val dialog = Dialog(this)
                val view = layoutInflater.inflate(R.layout.message_fragment,null)
                dialog.setContentView(view)
                dialog.window?.setBackgroundDrawable(ColorDrawable(TRANSPARENT))

                val inserter: TextView = view.findViewById(R.id.results)
                inserter.text = "Odds for COVID19 = 40%"

                dialog.show()
            }
            else{
                Toast.makeText(this, "Specify today's date and degree...", Toast.LENGTH_LONG).show()
            }




        }

    }
}
