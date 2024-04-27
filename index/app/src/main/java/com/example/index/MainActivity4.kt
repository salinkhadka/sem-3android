package com.example.index

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity4 : AppCompatActivity() {
    lateinit var autoText:AutoCompleteTextView;
    lateinit var button:Button;
    var data= arrayOf("Salin","khadka","ram")


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        autoText=findViewById(R.id.Apple1);
        button=findViewById(R.id.button2);


        button.setOnClickListener{
            Toast.makeText(this,autoText.text,Toast.LENGTH_LONG).show();
        }

        var adapter = ArrayAdapter(this@MainActivity4, android.R.layout.simple_dropdown_item_1line,data);

        autoText.setAdapter(adapter);
        autoText.threshold=1;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}