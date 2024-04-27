package com.example.task2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.media.Image
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var ImageV:ImageView;
    lateinit var nameText:TextView;
    lateinit var timeText:TextView;
    lateinit var addressText:AutoCompleteTextView;
    lateinit var male: RadioButton;
    lateinit var female: RadioButton;
    lateinit var  others: RadioButton;
    lateinit var  statusSpinner: Spinner;
    lateinit var  dateTextField: TextView;
    lateinit var  loadCalendar: Button;
    lateinit var  loadClk: Button;
    lateinit var  submitRegistration: Button;








    var addressOptions= arrayOf("Bhaisepati","Nakkhu","tokha","dillibazar","Pulchowk")
    var empStatus= arrayOf("Student","Employed","Unemployed","Self-employed")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ImageV=findViewById(R.id.imgView)

        male=findViewById(R.id.radioMale)
        female=findViewById(R.id.radioFemale)
        others=findViewById(R.id.radioOthers)

        loadClk=findViewById(R.id.timeButton)
        timeText=findViewById(R.id.timeTextView)
        nameText=findViewById(R.id.nameTextField)
        ImageV.setImageResource(R.drawable.img)
        addressText=findViewById(R.id.addressTextField)
        statusSpinner=findViewById(R.id.employmentStatus)
        dateTextField=findViewById(R.id.dateText)
        loadCalendar=findViewById(R.id.loadCal)
        submitRegistration=findViewById(R.id.submit)


        loadCalendar.setOnClickListener{
            loadCalender()
        }
        loadClk.setOnClickListener {
            loadClock()
        }
        submitRegistration.setOnClickListener{
            if (nameText.text.isEmpty() || addressText.text.isEmpty() || dateTextField.text.isEmpty()){
                Toast.makeText(this,"One of more text fields are empty",Toast.LENGTH_LONG).show()
            }
            else if (!male.isChecked && !female.isChecked && !others.isChecked){
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Select at least one gender",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            else if(timeText.text.isEmpty()){
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Time Not Selected")
                alertDialogBuilder.setMessage("Please select a time.")
                alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
                    // Do something if needed when the user clicks OK
                    // For example, you can dismiss the dialog
                    dialog.dismiss()
                }

                // Show the AlertDialog
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
            else{
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Sucessful registration",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }










        var adapter=ArrayAdapter(this@MainActivity,android.R.layout.simple_dropdown_item_1line,addressOptions);
        addressText.setAdapter(adapter)
        addressText.threshold=1

        var adapter2=ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_item,empStatus);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statusSpinner.setAdapter(adapter2)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadClock() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                // Format the selected time as a string
                val formattedTime = String.format("%02d:%02d", hourOfDay, minute)
                // Set the selected time to the timeText field
                timeText.text = formattedTime
            },
            currentHour,
            currentMinute,
            false // Whether the TimePicker is in 24-hour mode or not
        )

        // Show the TimePickerDialog
        timePickerDialog.show()
    }


    private fun loadCalender() {
        var C= Calendar.getInstance()
        var year =C.get(Calendar.YEAR)
        var month =C.get(Calendar.MONTH)
        var day =C.get(Calendar.DAY_OF_MONTH)

        var dateDialog= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ datePicker, yyyy, mm, dd ->
                dateTextField.text = "$yyyy,$mm,$dd"
            },
            year,
            month,
            day)
        dateDialog.show()


    }


}