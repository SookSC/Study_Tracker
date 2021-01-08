package com.example.study_tracker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_study_during.*

interface Communicator {
    fun comPassBreakNotes(textInputBreakNotes: String)
    fun comPassBreakDuration(timeBreak: Int)
    fun comPassStudyNotes(textInputStudyNotes: String)
    fun comPassStudyDuration(timeStudy: Int)
}

class HomeActivity : AppCompatActivity(), Communicator {
    var isStudyRunning = false
    val breakBeforeFragment = BreakBeforeFragment()
    val fragmentStudyBefore = StudyBeforeFragment()
    var timeBreakTotal = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_top,
            fragmentStudyBefore).commit()

        // Bottom navigation bar
        var intentLog = Intent(this, LogActivity::class.java)
        intentLog.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_log -> startActivity(intentLog)
                //Since already on home page, clicking home icon does nothing
                R.id.ic_home -> null
            }
            true
        }
    }

    override fun comPassStudyNotes(textInputStudyNotes: String) {
        isStudyRunning = true
        val bundle = Bundle()
        bundle.putString("textInputStudyNotes", textInputStudyNotes)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentStudyDuring = StudyDuringFragment()
        fragmentStudyDuring.arguments = bundle

        // Send study note to LogActivity
        val intent = Intent(this@HomeActivity, LogActivity::class.java)
        intent.putExtra("textInputStudyNotes", textInputStudyNotes)
        startActivity(intent)
        // Although LogActivity is started by the prev line, keep HomeActivity open on the screen
        var intentHome = Intent(this, HomeActivity::class.java)
        intentHome.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intentHome)

        transaction.replace(R.id.fragment_container_top, fragmentStudyDuring)
        transaction.replace(R.id.fragment_container_bottom, breakBeforeFragment)
        transaction.commit()
    }

    override fun comPassBreakNotes(textInputBreakNotes: String) {
        val bundle = Bundle()
        bundle.putString("textInputBreakNotes", textInputBreakNotes)

        val transaction = this.supportFragmentManager.beginTransaction()
        val breakDuringFragment = BreakDuringFragment()
        breakDuringFragment.arguments = bundle

        transaction.replace(R.id.fragment_container_bottom, breakDuringFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun comPassStudyDuration(timeStudy: Int) {
        isStudyRunning = false
        val breakButton = findViewById<Button>(R.id.breakDuringButton)

        if (breakButton != null) {
            breakButton.performClick()
        }
        val intent = Intent(this@HomeActivity, EndStudyActivity::class.java)
        intent.putExtra("timeStudy", timeStudy)
        intent.putExtra("timeBreak", timeBreakTotal)

        startActivity(intent)
    }

    override fun comPassBreakDuration(timeBreak: Int) {
        timeBreakTotal += timeBreak

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_bottom, breakBeforeFragment).commit()
        Toast.makeText(this@HomeActivity,"Break session recorded! The duration was "
                + (((timeBreak/1000)/60)/60).toString() + " hour(s) and " + (((timeBreak/1000)/60)%60).toString() + " minute(s) and " + ((timeBreak/1000)%60).toString()
                + " second(s).", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop();
        val startTime = SystemClock.elapsedRealtime() - studyChronometer.base
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putLong("startTime", startTime)
            putBoolean("isStudyRunning", isStudyRunning)
        }
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val startTime = sharedPreferences.getLong("startTime", 0)
        val isRunning = sharedPreferences.getBoolean("isStudyRunning", false)

        var currTime: Long = 0
        if (isRunning) {
            currTime = startTime + System.currentTimeMillis()
            studyChronometer.base = currTime
            studyChronometer.start()
        }

    }

}