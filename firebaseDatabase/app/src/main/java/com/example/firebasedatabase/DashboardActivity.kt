package com.example.firebasedatabase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedatabase.databinding.ActivityDashboardBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DashboardActivity : AppCompatActivity() {

    lateinit var DashboardActivity:ActivityDashboardBinding

    var database:FirebaseDatabase=FirebaseDatabase.getInstance()
    var ref:DatabaseReference=database.reference.child("products")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        DashboardActivity=ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(DashboardActivity.root)

        DashboardActivity.fab.setOnClickListener {
            var intent=Intent(this@DashboardActivity,AddProductActivity::class.java)
            startActivity(intent)
        }








        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }
}