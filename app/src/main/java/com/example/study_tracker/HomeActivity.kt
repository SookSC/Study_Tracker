package com.example.study_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer

interface Communicator {
    fun comPassStudyNotes(textInputStudyNotes: String)
    fun comPassStudyDuration(timeStudyMin: Int, timeStudySec: Int)
}

class HomeActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentStudyBefore = StudyBeforeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_top, fragmentStudyBefore).commit()
    }

    override fun comPassStudyNotes(textInputStudyNotes: String) {
        val bundle = Bundle()
        bundle.putString("textInputStudyNotes", textInputStudyNotes)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentStudyDuring = StudyDuringFragment()
        fragmentStudyDuring.arguments = bundle

        transaction.replace(R.id.fragment_container_top, fragmentStudyDuring).commit()

    }

    override fun comPassStudyDuration(timeStudyMin: Int, timeStudySec: Int) {
        val bundle = Bundle()
        bundle.putInt("timeStudyMin", timeStudyMin)
        bundle.putInt("timeStudySec", timeStudySec)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentEndSession = EndSessionFragment()
        fragmentEndSession.arguments = bundle

        transaction.replace(R.id.fragment_container_top, fragmentEndSession).commit()

    }
}