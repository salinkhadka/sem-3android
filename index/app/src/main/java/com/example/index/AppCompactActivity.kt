package com.example.index

import android.os.Bundle
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AppCompactActivity : AppCompatActivity() {
    lateinit var gridview:GridView;
    var nameList=ArrayList<String>();
    var imageList=ArrayList<Int>();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_compact)
        gridview=findViewById(R.id.grid)
        nameList.add("a")
        nameList.add("b")
        nameList.add("c")
//        nameList.add("img")
//        nameList.add("img1")
//        nameList.add("img2")
//        nameList.add("img3")

        imageList.add(R.drawable.a)
        imageList.add(R.drawable.b)
        imageList.add(R.drawable.c)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}