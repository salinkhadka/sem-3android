package com.example.index

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecyclerView : AppCompatActivity() {

    lateinit var rv: View;
    var titleList=ArrayList<String>();
    var descriptionofTitle=ArrayList<String>();
    var imageList=ArrayList<Int>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        titleList.add("apple");
        titleList.add("banana");
        titleList.add("cat");
//        titleList.add("dog");
//        titleList.add("eagle");
//        titleList.add("frog");
//        titleList.add("goat");

        imageList.add(R.drawable.a)
        imageList.add(R.drawable.b)
        imageList.add(R.drawable.c)

        descriptionofTitle.add("Syau")
        descriptionofTitle.add("kera")
        descriptionofTitle.add("biralo")

        rv=findViewById(R.id.viewRec)





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}