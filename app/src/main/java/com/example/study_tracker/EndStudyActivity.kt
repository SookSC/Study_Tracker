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
        var timeStudyHour = intent.getIntExtra("timeStudyHour", defaultValue)
        var timeStudyMin = intent.getIntExtra("timeStudyMin", defaultValue)
        var timeStudySec = intent.getIntExtra("timeStudySec", defaultValue)

        val textStudyHour = findViewById<TextView>(R.id.textStudyHour)
        val textStudyMin = findViewById<TextView>(R.id.textStudyMin)
        val textStudySec = findViewById<TextView>(R.id.textStudySec)

        textStudyHour.setText("$timeStudyHour hours")
        textStudyMin.setText("$timeStudyMin minutes")
        textStudySec.setText("$timeStudySec seconds")

        restartButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@EndStudyActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
