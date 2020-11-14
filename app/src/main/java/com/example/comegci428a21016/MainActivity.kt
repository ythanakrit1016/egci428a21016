package com.example.comegci428a21016

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_layout.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var lasUpdate: Long = 0
    private var newNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar.setCustomView(R.layout.menu_layout)
        floatTV.text = "Select Lucky Number"

        textView21.setText("Shake For Your\nLucky Number")
        textView22.setBackgroundResource(R.drawable.text_bg_white)
        textView23.setText("")
        imageView21.setImageResource(R.drawable.bg_transparent)
        textView24.setText("")
        textView25.setText("")
        button21.setText("Check Lucky Draw")

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lasUpdate = System.currentTimeMillis()

        button21.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("newNum",newNum)
            startActivity(intent)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
    }

    private fun getAccelerometer(event: SensorEvent) {
        val values = event.values
        val x = values[0]
        val y = values[1]
        val z = values[2]

        val accel = (x*x + y*y + z*z)/(SensorManager.GRAVITY_EARTH* SensorManager.GRAVITY_EARTH)
        val actualTime = System.currentTimeMillis()
        if (accel>2) {
            if(actualTime-lasUpdate < 200) {
                return
            }
            lasUpdate = actualTime

            val num = kotlin.random.Random.nextInt(20, 40)

            newNum = num
            textView22.text = num.toString()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }
}