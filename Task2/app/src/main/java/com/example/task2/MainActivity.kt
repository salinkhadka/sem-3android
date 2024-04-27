package com.example.task2

import android.app.DatePickerDialog
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
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var ImageV:ImageView;
    lateinit var nameText:TextView;
    lateinit var addressText:AutoCompleteTextView;
    lateinit var male: RadioButton;
    lateinit var female: RadioButton;
    lateinit var  others: RadioButton;
    lateinit var  statusSpinner: Spinner;
    lateinit var  dateTextField: TextView;
    lateinit var  loadCalendar: Button;
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
        submitRegistration.setOnClickListener{
            if (nameText.text.isEmpty() || addressText.text.isEmpty() || dateTextField.text.isEmpty()){
                Toast.makeText(this,"One of more text fields are empty",Toast.LENGTH_LONG).show()
            }
            else if (!male.isChecked && !female.isChecked && !others.isChecked){
                Toast.makeText(this,"Select atleast   one gender",Toast.LENGTH_LONG).show()
            }
            println(nameText.text)
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