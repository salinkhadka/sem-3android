package com.example.firebasedatabase.Ui.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedatabase.R
import com.example.firebasedatabase.databinding.ActivitySensorDashboardBinding

class SensorDashboardActivity : AppCompatActivity() {
    lateinit var sensorDashboardBinding: ActivitySensorDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sensorDashboardBinding=ActivitySensorDashboardBinding.inflate(layoutInflater)
        setContentView(sensorDashboardBinding.root)



        sensorDashboardBinding.buttonSensor.setOnClickListener {
            var intent=Intent(this@SensorDashboardActivity,SensorListActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}