package com.example.index

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListViewActicity : AppCompatActivity() {
    lateinit var listView:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view_acticity)
        var data=resources.getStringArray(R.array.Country)
        listView=findViewById(R.id.apple)

        var adapter=ArrayAdapter(this@ListViewActicity,android.R.layout.simple_list_item_1,data)
        listView.adapter=adapter
        listView.setOnItemClickListener { adapterView, view, index, l ->
            Toast.makeText(
                applicationContext, adapterView.getItemAtPosition(index).toString(),
                Toast.LENGTH_LONG
            ).show()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}