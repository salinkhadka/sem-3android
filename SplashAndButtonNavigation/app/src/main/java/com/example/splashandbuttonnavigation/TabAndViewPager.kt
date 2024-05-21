package com.example.splashandbuttonnavigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import com.example.splashandbuttonnavigation.databinding.ActivityTabAndViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabAndViewPager : AppCompatActivity() {
    lateinit var Binding: ActivityTabAndViewPagerBinding
    var heading= arrayOf("Message","Status","Call")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Binding=ActivityTabAndViewPagerBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        var fragmentManager :FragmentManager=supportFragmentManager
        Binding.viewPager2.adapter=TabAdapter(fragmentManager,lifecycle)

        TabLayoutMediator(Binding.tabLayout,Binding.viewPager2){
            tabs,position ->tabs.text =heading[position]
        }.attach()




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}