package com.example.study_tracker

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_study_before.*
import kotlinx.android.synthetic.main.fragment_study_during.view.*

private lateinit var viewOfLayout: View

class StudyDuringFragment : Fragment() {

    private lateinit var communicator: EndSessionCommunicator

    var textStudyNotes: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        communicator = activity as EndSessionCommunicator

        val view = inflater.inflate(R.layout.fragment_study_during, container, false)
        textStudyNotes = arguments?.getString("textStudyNotes")
        view.textStudyNotes.text = textStudyNotes

        val studyChronometer = view.findViewById<Chronometer>(R.id.studyChronometer)
        val studyButton = view.findViewById<Button>(R.id.studyButton)

        studyChronometer.setBase(SystemClock.elapsedRealtime())
        studyChronometer.start()

        studyButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var elapsedMillis: Long = SystemClock.elapsedRealtime() - studyChronometer.base
                studyChronometer.setBase(SystemClock.elapsedRealtime())
                studyChronometer.stop()
                communicator.passDataComEndSession(elapsedMillis)
            }
        })

        return view
    }
}