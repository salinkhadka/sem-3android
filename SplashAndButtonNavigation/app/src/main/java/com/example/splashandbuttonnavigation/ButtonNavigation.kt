package com.example.splashandbuttonnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.splashandbuttonnavigation.databinding.ActivityButtonNavigationBinding
import com.google.android.material.navigation.NavigationView

class ButtonNavigation : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    lateinit var ButtonNavBind:ActivityButtonNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ButtonNavBind=ActivityButtonNavigationBinding.inflate(layoutInflater)
        setContentView(ButtonNavBind.root)







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.home->{
                title="Home"
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout,HomeFragment()).commit()
            }
            R.id.search->{
                title="Search"
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout,SecondFragment()).commit()
            }
            R.id.notification->{
                title="Notification"
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout,ThirdFragment()).commit()
            }
            R.id.profile->{
                title="Profile"
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout,SecondFragment()).commit()
            }

        }
    }
}