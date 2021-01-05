package com.example.study_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_home.*

interface Communicator {
    fun comPassBreakNotes(breakEditText_input: String)
    fun comPassBreakDuration(timeBreakHour: Int, timeBreakMin: Int, timeBreakSec: Int)
    fun comPassStudyNotes(textInputStudyNotes: String)
    fun comPassStudyDuration(timeStudyMin: Int, timeStudySec: Int, timeStudyHour: Int)
}

class HomeActivity : AppCompatActivity(), Communicator {
    val breakBeforeFragment = BreakBeforeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentStudyBefore = StudyBeforeFragment()

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_top,
            fragmentStudyBefore).commit()

        // Bottom navigation bar
        val intent = Intent(this, LogActivity::class.java)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container_top,
                    fragmentStudyBefore).commit()
                R.id.ic_log -> startActivity(intent)
            }
            true
        }
    }

    override fun comPassStudyNotes(textInputStudyNotes: String) {
        val bundle = Bundle()
        bundle.putString("textInputStudyNotes", textInputStudyNotes)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentStudyDuring = StudyDuringFragment()
        fragmentStudyDuring.arguments = bundle

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

    override fun comPassStudyDuration(timeStudyMin: Int, timeStudySec: Int, timeStudyHour: Int) {
        val intent = Intent(this@HomeActivity, EndStudyActivity::class.java)
        intent.putExtra("timeStudyMin", timeStudyMin)
        intent.putExtra("timeStudySec", timeStudySec)
        intent.putExtra("timeStudyHour", timeStudyHour)
        startActivity(intent)
    }

    override fun comPassBreakDuration(timeBreakHour: Int, timeBreakMin: Int, timeBreakSec: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_bottom, breakBeforeFragment).commit()
        Toast.makeText(this@HomeActivity,"Break session recorded! The duration was "
                + timeBreakHour.toString() + " hour(s) and " + timeBreakMin.toString() + " minute(s) and " + timeBreakSec.toString()
                + " second(s).", Toast.LENGTH_SHORT).show()
    }

}