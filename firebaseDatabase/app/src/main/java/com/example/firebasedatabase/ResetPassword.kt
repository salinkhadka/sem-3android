package com.example.firebasedatabase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedatabase.databinding.ActivityResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ResetPassword : AppCompatActivity() {
    lateinit var ResetPassword:ActivityResetPasswordBinding
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ResetPassword=ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(ResetPassword.root)
        ResetPassword.resetBut.setOnClickListener {
            var email:String=ResetPassword.resetEmail.text.toString()




            auth.sendPasswordResetEmail(email).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Email sent", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(applicationContext,it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}