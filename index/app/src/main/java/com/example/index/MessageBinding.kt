package com.example.index

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.index.databinding.ActivityMessageBindingBinding

class MessageBinding : AppCompatActivity() {
    lateinit var sharedPreference:SharedPreferences;
lateinit var messageBindingBinding: ActivityMessageBindingBinding

var counter=0
    var username:String?=null;
    var message:String?=null;
    var remember:Boolean=false;

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Message","On create called")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_message_binding)
        messageBindingBinding=ActivityMessageBindingBinding.inflate(layoutInflater)
        var view=messageBindingBinding.root
        setContentView(view)

        messageBindingBinding.but.setOnClickListener{
//            var username=messageBindingBinding.username.text.toString()
            counter++

            messageBindingBinding.but.text=""+counter;
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        Log.d("Message","On start called")
        super.onStart()
    }

    override fun onPause() {
        Log.d("Message","On Pause called")
        sharedPreference=getSharedPreferences("UserData", MODE_PRIVATE)
        username=messageBindingBinding.username.text.toString()
        message=messageBindingBinding.mesage.text.toString()
        remember=messageBindingBinding.rememberMe.isChecked
        var editor=sharedPreference.edit()
        editor.putString("username",username)
        editor.putString("message",username)
        editor.putBoolean("remember",remember)
        editor.putInt("counter",counter)

        editor.apply()//asynchronous doesn't wait for other methods
//        editor.commit() synchronous it waits for in the stack

        Toast.makeText(this,"Data is saved",Toast.LENGTH_LONG).show()



        super.onPause()
    }

    override fun onDestroy() {
        Log.d("Message","On destroy called")

        super.onDestroy()
    }

    override fun onStop() {
        Log.d("Message","On Stop called")
        super.onStop()
    }

    override fun onResume() {

        Log.d("Message","On resume called")
        sharedPreference=getSharedPreferences("UserData", MODE_PRIVATE)
        var editor=sharedPreference.edit()
        username=sharedPreference.getString("username","")
        message=sharedPreference.getString("message","")
        remember=sharedPreference.getBoolean("remember",false)
        counter=sharedPreference.getInt("counter",0)

        messageBindingBinding.username.setText(username)
        messageBindingBinding.mesage.setText(message)
        messageBindingBinding.rememberMe.isChecked=remember
        messageBindingBinding.but.text=counter.toString()




        super.onResume()
    }

    override fun onRestart() {
        Log.d("Message","On restart called")
        super.onRestart()
    }
}
