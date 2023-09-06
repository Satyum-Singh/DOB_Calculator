package com.example.dob_calculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvDate1 : TextView? = null
    private var ageInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPicker : Button = findViewById(R.id.btnPicker)

        tvDate1 = findViewById(R.id.tvDate1)
        ageInMinutes = findViewById(R.id.ageInMinutes)

        btnPicker.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_,selectedYear,selectedMonth,selectedDay ->
                Toast.makeText(this,
                    "The year is $selectedYear, the month is ${selectedMonth+1}, and the day is $selectedDay",
                    Toast.LENGTH_SHORT).show()

                val selectedDate = "$selectedDay  /  ${selectedMonth+1}  /  $selectedYear"
                tvDate1?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let{
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        ageInMinutes?.text = differenceInMinutes.toString()
                    }
                }

            },
            year,
            month,
            day)

//        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}