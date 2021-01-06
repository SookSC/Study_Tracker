package com.example.study_tracker

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_break_before.*
import kotlinx.android.synthetic.main.fragment_study_during.view.*

private lateinit var viewOfLayout: View

class StudyDuringFragment : Fragment() {

    private lateinit var communicator: Communicator

    var textInputStudyNotes: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        communicator = activity as Communicator

        val view = inflater.inflate(R.layout.fragment_study_during, container, false)
        textInputStudyNotes = arguments?.getString("textInputStudyNotes")
        view.textStudyNotes.text = textInputStudyNotes

        val studyChronometer = view.findViewById<Chronometer>(R.id.studyChronometer)
        val studyButton = view.findViewById<Button>(R.id.studyButton)

        var startTime = SystemClock.elapsedRealtime()

        studyChronometer.setBase(startTime)
        studyChronometer.start()

        studyButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var endTime = SystemClock.elapsedRealtime()

                studyChronometer.setBase(endTime)
                studyChronometer.stop()

                var timeStudy = endTime - startTime

                communicator.comPassStudyDuration(timeStudy.toInt())
            }
        })

        return view
    }
}