package com.example.splashandbuttonnavigation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.splashandbuttonnavigation.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding


    private fun replaceFragment(fragment: Fragment) {
       var fragmentManager:FragmentManager=supportFragmentManager
        var fragmentTransaction:FragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->replaceFragment(HomeFragment())
                R.id.search->replaceFragment(SecondFragment())
                R.id.notification->replaceFragment(ThirdFragment())
                R.id.profile->replaceFragment(FourthFragment())

                else->{
                    replaceFragment(ErrorFragment())
                }
            }
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}