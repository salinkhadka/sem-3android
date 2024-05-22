package com.example.gametask

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gametask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var MainActivity:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MainActivity=ActivityMainBinding.inflate(layoutInflater)
        setContentView(MainActivity.root)

        MainActivity.AdditionButton.setOnClickListener {
            var intent=Intent(this@MainActivity,GamePage::class.java)
            var operation:String="add"
            intent.putExtra("operator",operation)

            startActivity(intent)
            finish()



        }
        MainActivity.SubtractionButton.setOnClickListener {
            var intent=Intent(this@MainActivity,GamePage::class.java)
            var operation:String="sub"
            intent.putExtra("operator",operation)

            startActivity(intent)
            finish()



        }
        MainActivity.MultiplicationButton.setOnClickListener {
            var intent=Intent(this@MainActivity,GamePage::class.java)
            var operation:String="mul"
            intent.putExtra("operator",operation)

            startActivity(intent)
            finish()



        }









        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets




        }
    }
}