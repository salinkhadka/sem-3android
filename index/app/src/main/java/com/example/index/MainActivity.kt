package com.example.index

import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var Red:RadioButton;
    lateinit var Blue:RadioButton;
    lateinit var  Green:RadioButton;
    lateinit var weirdImageContainer:ImageView;
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Red =findViewById(R.id.Red);
        Blue =findViewById(R.id.Blue);
        Green =findViewById(R.id.Green);
        weirdImageContainer=findViewById(R.id.weirdImageContainer)


        Red.setOnClickListener{
            weirdImageContainer.setImageResource(R.drawable.a);

        }
        Blue.setOnClickListener{
            weirdImageContainer.setImageResource(R.drawable.b);

        }
        Green.setOnClickListener{
            weirdImageContainer.setImageResource(R.drawable.c);

        }


        
    }
}