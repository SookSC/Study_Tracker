package com.example.study_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class EndStudyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_study)
        val defaultValue = 1
        var intent = intent
        var timeStudyHour = intent.getIntExtra("timeStudyHour", defaultValue)
        var timeStudyMin = intent.getIntExtra("timeStudyMin", defaultValue)
        var timeStudySec = intent.getIntExtra("timeStudySec", defaultValue)

        val textStudyHour = findViewById<TextView>(R.id.textStudyHour)
        val textStudyMin = findViewById<TextView>(R.id.textStudyMin)
        val textStudySec = findViewById<TextView>(R.id.textStudySec)

        textStudyHour.setText("$timeStudyHour hours")
        textStudyMin.setText("$timeStudyHour minutes")
        textStudySec.setText("$timeStudySec seconds")
    }
}
