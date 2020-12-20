package com.example.study_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studyChronometer = findViewById<Chronometer>(R.id.studyChronometer)
        val breakChronometer = findViewById<Chronometer>(R.id.breakChronometer)

        val studyButton = findViewById<Button>(R.id.studyButton)

        studyButton?.setOnClickListener(object : View.OnClickListener {
            var isRunning = false

            override fun onClick(v: View?) {
                if (!isRunning) {
                    studyChronometer.start()
                    isRunning = true
                }

                else {
                    studyChronometer.stop()
                    isRunning = false
                }

                studyButton.setText(if (isRunning) R.string.button_study_start else R.string.button_study_stop )

                TODO("add toast")
            }

        })

        val breakButton = findViewById<Button>(R.id.breakButton)
    }
}