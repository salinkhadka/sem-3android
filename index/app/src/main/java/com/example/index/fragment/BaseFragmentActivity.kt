package com.example.index.fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.index.R
import com.example.index.databinding.ActivityBaseFragmentBinding

class BaseFragmentActivity : AppCompatActivity() {
    lateinit var bind:ActivityBaseFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind=ActivityBaseFragmentBinding.inflate(layoutInflater)
        setContentView(bind.root)
        replaceFragment(firstFragment())


        bind.button3.setOnClickListener{
            replaceFragment(firstFragment())

        }

        bind.button4.setOnClickListener{
            replaceFragment(secondFragment())
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun replaceFragment(fragment:Fragment,) {
        var fragmentManager:FragmentManager=supportFragmentManager
        var fragmentTransaction:FragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_frag,fragment)
        fragmentTransaction.commit()

    }
}