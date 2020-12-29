package com.example.study_tracker

import android.R.attr.key
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_study_before.*


private lateinit var viewOfLayout: View

class StudyBeforeFragment : Fragment() {

    private lateinit var communicator: StudyCommunicator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_study_before, container, false)

        communicator = activity as StudyCommunicator

        val breakButton = view.findViewById<Button>(R.id.studyButton)

        breakButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var editTextStudyNotes = editTextStudyNotes.text.toString()
                communicator.passDataComStudy(editTextStudyNotes)

            }
        })

        return view
    }
}