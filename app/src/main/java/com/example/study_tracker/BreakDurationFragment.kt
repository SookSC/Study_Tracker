package com.example.study_tracker

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_break_duration.view.*

class BreakDurationFragment : Fragment() {
    var inputText: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_break_duration, container, false)
        inputText = arguments?.getString("input_txt").toString()

        v.output_textview.text = inputText

        return v
    }
}