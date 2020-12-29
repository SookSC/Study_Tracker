package com.example.study_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer

interface StudyCommunicator {
    fun passDataComStudy(textStudyNotes: String)
}

interface EndSessionCommunicator {
    fun passDataComEndSession(textStudyTime: Long)
}

class HomeActivity : AppCompatActivity(), StudyCommunicator, EndSessionCommunicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentStudyBefore = StudyBeforeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentStudyBefore).commit()


        /*val studyChronometer = findViewById<Chronometer>(R.id.studyChronometer)
        val breakChronometer = findViewById<Chronometer>(R.id.breakChronometer)

        val studyButton = findViewById<Button>(R.id.studyButton)
        val breakButton = findViewById<Button>(R.id.breakButton)

        studyButton?.setOnClickListener(object : View.OnClickListener {
            var isRunning = false

            override fun onClick(v: View?) {
                if (!isRunning) {
                    studyChronometer.setBase(SystemClock.elapsedRealtime())
                    studyChronometer.start()
                    isRunning = true
                } else {
                    studyChronometer.setBase(SystemClock.elapsedRealtime())
                    studyChronometer.stop()
                    breakChronometer.setBase(SystemClock.elapsedRealtime())
                    breakChronometer.stop()
                    isRunning = false
                }

                if (isRunning) {
                    breakChronometer.isEnabled = true
                    breakButton.isEnabled = true
                }
                else {
                    breakChronometer.isEnabled = false
                    breakButton.isEnabled = false
                }

                studyButton.setText(if (isRunning) R.string.button_study_stop else R.string.button_study_start)

            }

        })

        breakButton?.setOnClickListener(object : View.OnClickListener {
            var isRunning = false

            override fun onClick(v: View?) {
                if (!isRunning) {
                    breakChronometer.setBase(SystemClock.elapsedRealtime())
                    breakChronometer.start()
                    isRunning = true
                } else {
                    breakChronometer.setBase(SystemClock.elapsedRealtime())
                    breakChronometer.stop()
                    isRunning = false
                }

                breakButton.setText(if (isRunning) R.string.button_break_stop else R.string.button_break_start)

            }

        })*/
    }

    override fun passDataComStudy(textStudyNotes: String) {
        val bundle = Bundle()
        bundle.putString("textStudyNotes", textStudyNotes)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentStudyDuring = StudyDuringFragment()
        fragmentStudyDuring.arguments = bundle

        transaction.replace(R.id.fragment_container, fragmentStudyDuring).commit()

    }

    override fun passDataComEndSession(textStudyTime: Long) {
        val bundle = Bundle()
        bundle.putLong("textStudyTime", textStudyTime)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentEndSession = EndSessionFragment()
        fragmentEndSession.arguments = bundle

        transaction.replace(R.id.fragment_container, fragmentEndSession).commit()

    }
}