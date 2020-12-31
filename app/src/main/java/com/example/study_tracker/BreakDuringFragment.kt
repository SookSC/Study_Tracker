package com.example.study_tracker

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Chronometer
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_break_during.*
import kotlinx.android.synthetic.main.fragment_break_during.view.*
import kotlinx.android.synthetic.main.fragment_break_during.view.breakDuringButton
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_break_before.view.*


class BreakDuringFragment : Fragment() {
    var inputText: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_break_during, container, false)
        inputText = arguments?.getString("breakInputText").toString()

        v.output_textview.text = inputText

        val breakChronometer = v.findViewById<Chronometer>(R.id.breakChronometer)

        var startTime = SystemClock.elapsedRealtime()

        breakChronometer.setBase(startTime)
        breakChronometer.start()

        //Close this fragment on button click
        lateinit var comm: Communicator
        comm = activity as Communicator

        v.breakDuringButton.setOnClickListener {
            var endTime = SystemClock.elapsedRealtime()
            breakChronometer.setBase(endTime)
            breakChronometer.stop()

            var breakDurationMinutes = ((endTime - startTime)/1000)/60
            var breakDurationSeconds = ((endTime - startTime)/1000)%60

            comm.passBreakDurationCom(breakDurationMinutes.toInt(), breakDurationSeconds.toInt())
        }

        return v
    }
}