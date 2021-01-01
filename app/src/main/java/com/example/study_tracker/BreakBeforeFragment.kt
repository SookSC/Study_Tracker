package com.example.study_tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_break_before.*
import kotlinx.android.synthetic.main.fragment_break_before.view.*

class BreakBeforeFragment : Fragment() {
    lateinit var comm: Communicator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_break_before, container, false)

        comm = activity as Communicator

        v.breakButton.setOnClickListener {
            comm.comPassBreakNotes(v.editTextBreakNotes.text.toString())
            editTextBreakNotes.setText(null)
        }

        return v
    }
}