package com.example.study_tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.os.SystemClock
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_break_during.view.*
import kotlinx.android.synthetic.main.fragment_break_during.view.breakDuringButton

class BreakDuringFragment : Fragment() {
    var inputText: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_break_during, container, false)
        inputText = arguments?.getString("textInputBreakNotes").toString()

        v.textBreakNotes.text = inputText

        val breakChronometer = v.findViewById<Chronometer>(R.id.breakChronometer)

        var startTime = SystemClock.elapsedRealtime()

        breakChronometer.setBase(startTime)
        breakChronometer.start()

        lateinit var comm: Communicator
        comm = activity as Communicator


        v.breakDuringButton.setOnClickListener {
            var endTime = SystemClock.elapsedRealtime()
            breakChronometer.setBase(endTime)
            breakChronometer.stop()

            var timeBreak = endTime - startTime
            //var breakDurationHours = (((endTime - startTime)/1000)/60)/60
            //var breakDurationMinutes = (((endTime - startTime)/1000)/60)%60
            //var breakDurationSeconds = ((endTime - startTime)/1000)%60

            comm.comPassBreakDuration(timeBreak.toInt())
        }

        return v
    }
}