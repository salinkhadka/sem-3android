package com.example.firebasedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedatabase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var MainActivity:ActivityMainBinding
    var auth:FirebaseAuth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        MainActivity=ActivityMainBinding.inflate(layoutInflater)
        setContentView(MainActivity.root)

        MainActivity.buttonSU.setOnClickListener {
            var email:String=MainActivity.EmailInput.text.toString()
            var password:String=MainActivity.PasswordInput.text.toString()

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Registration sucess",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(applicationContext,it.exception?.message,Toast.LENGTH_LONG).show()
                }
            }



        }
        MainActivity.buttonLI.setOnClickListener {
            var email:String=MainActivity.EmailInput.text.toString()
            var password:String=MainActivity.PasswordInput.text.toString()

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Login sucess",Toast.LENGTH_LONG).show()
                    var intent=Intent(this@MainActivity,DashboardActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext,it.exception?.message,Toast.LENGTH_LONG).show()
                }
            }
        }




        MainActivity.forgetPW.setOnClickListener {
            var intent=Intent(this@MainActivity,ResetPassword::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}