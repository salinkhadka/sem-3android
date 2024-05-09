package com.example.index.fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.index.R
import com.example.index.databinding.ActivityBaseButtonBinding


class BaseButton : AppCompatActivity() {
    lateinit var baseBinding:ActivityBaseButtonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        baseBinding= ActivityBaseButtonBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)

        baseBinding.baseButton.setOnClickListener {

        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}