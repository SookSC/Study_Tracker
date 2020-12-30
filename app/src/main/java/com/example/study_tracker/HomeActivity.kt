package com.example.study_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_break_before.*
import kotlinx.android.synthetic.main.fragment_break_during.*

interface Communicator {
    fun passDataCom(breakEditText_input: String)
    fun openBreakBeforeFragmentCom()
}

val breakFragment1 = BreakBeforeFragment()

class HomeActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
/*
        val studyChronometer = findViewById<Chronometer>(R.id.studyChronometer)
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

        })
*/
        supportFragmentManager.beginTransaction().replace(R.id.content_id, breakFragment1).commit()

    }

    override fun passDataCom(breakEditText_input: String) {
        val bundle = Bundle()
        bundle.putString("breakInputText", breakEditText_input)

        val transaction = this.supportFragmentManager.beginTransaction()
        val breakFragment2 = BreakDuringFragment()
        breakFragment2.arguments = bundle

        transaction.replace(R.id.content_id, breakFragment2)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    // Open BreakBeforeFragment once break timer is stopped
    override fun openBreakBeforeFragmentCom() {
        supportFragmentManager.beginTransaction().replace(R.id.content_id, breakFragment1).commit()
        Toast.makeText(this@HomeActivity, "Break session recorded!", Toast.LENGTH_SHORT).show()
    }


}