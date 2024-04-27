package com.example.index

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.google.android.material.snackbar.Snackbar

class MainActivity3 : AppCompatActivity() {
    lateinit var CBToast:CheckBox;
    lateinit var CBSnackbar:CheckBox;
    lateinit var CBAlert:CheckBox;
    lateinit var button1:Button;
    lateinit var main:ConstraintLayout;



    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        CBToast =findViewById(R.id.Toast);
        CBSnackbar =findViewById(R.id.SnackBar);
        CBAlert =findViewById(R.id.Alert);
        button1=findViewById(R.id.button)
        main = findViewById(R.id.main)
        button1.setOnClickListener {
            if (CBToast.isChecked) {
                Toast.makeText(this,"this is toast",Toast.LENGTH_LONG).show()
            }
           else if (CBSnackbar.isChecked) {
              Snackbar.make(main,"this is snack",Snackbar.LENGTH_LONG).setAction("no",{}).show()
           }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}