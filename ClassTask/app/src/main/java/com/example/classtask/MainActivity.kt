package com.example.classtask

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.classtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences;
    var uname: String? = null;
    var pwd: String? = null;
    var remember: Boolean? = false;




    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)



        //for remember me
        sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)
        remember=sharedPreferences.getBoolean("RememberME",false)
        uname=sharedPreferences.getString("Username",null)
        pwd=sharedPreferences.getString("Password",null)

        if(remember==true){
            binding.editTextText.setText(uname)
            binding.editTextTextPassword.setText(pwd)

        }

        binding.Reg.setOnClickListener {

            sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)

            var editor = sharedPreferences.edit()
            uname = binding.editTextText.text.toString()
            pwd = binding.editTextTextPassword.text.toString()
            remember = binding.checkBox.isChecked
            editor.putString("Username", uname)
            editor.putString("Password", pwd)
            editor.putBoolean("RememberME", remember!!)
            editor.apply()

            Toast.makeText(this,"Registered sucessfully",Toast.LENGTH_LONG).show()


        }
        binding.Log.setOnClickListener {
            sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)

            uname=sharedPreferences.getString("Username",null)
            pwd=sharedPreferences.getString("Password",null)

            var editor = sharedPreferences.edit()
            editor.putBoolean("RememberME", binding.checkBox.isChecked)

            editor.apply()
            Log.d("Message",binding.checkBox.isChecked.toString())

            if (uname.toString()==binding.editTextText.text.toString() && pwd==binding.editTextTextPassword.text.toString()) {
                var intent = Intent(this@MainActivity, Dashboard::class.java)
                Toast.makeText(this,"Sucessfully logged in",Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"No user found",Toast.LENGTH_LONG).show()

            }

        }







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}