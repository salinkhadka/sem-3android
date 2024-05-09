package com.example.index

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirsttAct : AppCompatActivity() {
    lateinit var navigate:Button;
    lateinit var textData:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firstt)
        navigate=findViewById(R.id.nav)
        textData=findViewById(R.id.dataPass)

        //passing data
        navigate.setOnClickListener{
            var intent=Intent(this@FirsttAct,MainActivity2::class.java)
            var passText:String = textData.text.toString();
            intent.putExtra("key1",passText)
            startActivity(intent)
//            finish()

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}