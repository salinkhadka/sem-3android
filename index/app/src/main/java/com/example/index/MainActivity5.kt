package com.example.index

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity5 : AppCompatActivity() {
    lateinit var Dbutton:Button;
    lateinit var DateText:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main5)
        super.onCreate(savedInstanceState)
        Dbutton=findViewById(R.id.DateButton)
        DateText=findViewById(R.id.TextView4)

        Dbutton.setOnClickListener{
            loadCalender()
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadCalender() {
        var C=Calendar.getInstance()
        var year =C.get(Calendar.YEAR)
        var month =C.get(Calendar.MONTH)
        var day =C.get(Calendar.DAY_OF_MONTH)

        var dateDialog=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{datePicker,yyyy,mm,dd ->
                DateText.text = "$yyyy,$mm,$dd"
            },
            year,
            month,
            day)
        dateDialog.show()


    }
}