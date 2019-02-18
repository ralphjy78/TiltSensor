package com.ralph.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        // 가로모드
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // 화면이 꺼지지 않게
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d("MainActivity", "onAccuracyChanged: ${accuracy}")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {    // let는 null이 아니면 람다를 수행하고 null이면 아무 일도 일어나지 않음
            Log.d("MainActivity", "onSensorChanged: x: ${event.values[0]}, y: ${event.values[1]}, z: ${event.values[2]}")
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
