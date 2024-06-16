package com.example.firebasedatabase.Ui.Activity

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedatabase.R
import com.example.firebasedatabase.databinding.ActivitySensorListBinding

class SensorListActivity : AppCompatActivity() {
    lateinit var sensorListActivity:ActivitySensorListBinding
    lateinit var sensorManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sensorListActivity=ActivitySensorListBinding.inflate(layoutInflater)
        setContentView(sensorListActivity.root)

        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager

        var sensorlist=sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (sensor in sensorlist){
            sensorListActivity.textSensor.append(sensor.name+"\n")
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}