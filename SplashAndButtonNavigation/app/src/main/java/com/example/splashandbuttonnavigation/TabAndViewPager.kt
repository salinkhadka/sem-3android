package com.example.splashandbuttonnavigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.splashandbuttonnavigation.databinding.ActivityTabAndViewPagerBinding

class TabAndViewPager : AppCompatActivity() {
    lateinit var Binding: ActivityTabAndViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Binding=ActivityTabAndViewPagerBinding.inflate(layoutInflater)
        setContentView(Binding.root)






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}