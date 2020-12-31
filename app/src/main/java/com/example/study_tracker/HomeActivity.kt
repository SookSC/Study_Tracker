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
    fun passBreakInputTextCom(breakEditText_input: String)
    fun passBreakDurationCom(breakDurationMin: Int, breakDurationSec: Int)
}

class HomeActivity : AppCompatActivity(), Communicator {
    val breakBeforeFragment = BreakBeforeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().replace(R.id.content_id, breakBeforeFragment).commit()

    }

    override fun passBreakInputTextCom(breakEditText_input: String) {
        val bundle = Bundle()
        bundle.putString("breakInputText", breakEditText_input)

        val transaction = this.supportFragmentManager.beginTransaction()
        val breakDuringFragment = BreakDuringFragment()
        breakDuringFragment.arguments = bundle

        transaction.replace(R.id.content_id, breakDuringFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun passBreakDurationCom(breakDurationMin: Int, breakDurationSec: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.content_id, breakBeforeFragment).commit()
        Toast.makeText(this@HomeActivity,"Break session recorded! The duration was "
                + breakDurationMin.toString() + " minute(s) and " + breakDurationSec.toString()
                + " second(s).", Toast.LENGTH_SHORT).show()
    }


}