package com.example.classtask

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class Dashboard : AppCompatActivity() {
    lateinit var recycle:RecyclerView;
    var ListOfNumber=ArrayList<Int>();
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        recycle=findViewById(R.id.viewRecycle)
        ListOfNumber.add(1);
        ListOfNumber.add(2);
        ListOfNumber.add(3);
        ListOfNumber.add(4);
        ListOfNumber.add(5);
        ListOfNumber.add(6);
        ListOfNumber.add(7);
        ListOfNumber.add(8);
        ListOfNumber.add(9);
        ListOfNumber.add(10);





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}