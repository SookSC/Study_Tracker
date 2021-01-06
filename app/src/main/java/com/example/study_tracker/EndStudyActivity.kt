package com.example.study_tracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_end_study.*


class EndStudyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_study)
        val defaultValue = 1
        var intent = intent
        var timeStudy = intent.getIntExtra("timeStudy", defaultValue)
        var timeBreak = intent.getIntExtra("timeBreak", defaultValue)

        var timeTotalHour = (((timeStudy)/1000)/60)/60
        var timeTotalMin = (((timeStudy)/1000)/60)%60
        var timeTotalSec = ((timeStudy)/1000)%60

        timeStudy -= timeBreak

        var timeBreakHour = (((timeBreak)/1000)/60)/60
        var timeBreakMin = (((timeBreak)/1000)/60)%60
        var timeBreakSec = ((timeBreak)/1000)%60

        var timeStudyHour = (((timeStudy)/1000)/60)/60
        var timeStudyMin = (((timeStudy)/1000)/60)%60
        var timeStudySec = ((timeStudy)/1000)%60

        val textBreakHour = findViewById<TextView>(R.id.textBreakHour)
        val textBreakMin = findViewById<TextView>(R.id.textBreakMin)
        val textBreakSec = findViewById<TextView>(R.id.textBreakSec)

        val textStudyHour = findViewById<TextView>(R.id.textStudyHour)
        val textStudyMin = findViewById<TextView>(R.id.textStudyMin)
        val textStudySec = findViewById<TextView>(R.id.textStudySec)

        val textTotalHour = findViewById<TextView>(R.id.textTotalHour)
        val textTotalMin = findViewById<TextView>(R.id.textTotalMin)
        val textTotalSec = findViewById<TextView>(R.id.textTotalSec)

        textStudyHour.setText("$timeStudyHour hours")
        textStudyMin.setText("$timeStudyMin minutes")
        textStudySec.setText("$timeStudySec seconds")

        textBreakHour.setText("$timeBreakHour hours")
        textBreakMin.setText("$timeBreakMin minutes")
        textBreakSec.setText("$timeBreakSec seconds")

        textTotalHour.setText("$timeTotalHour hours")
        textTotalMin.setText("$timeTotalMin minutes")
        textTotalSec.setText("$timeTotalSec seconds")

        restartButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@EndStudyActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
