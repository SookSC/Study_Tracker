package com.example.study_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_break_before.*

private lateinit var viewOfLayout: View

class BreakBeforeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_break_before, container, false)

        val breakButton = v.findViewById<Button>(R.id.breakButton)

        breakButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                var editTextBreakNotes = editTextBreakNotes.text.toString()

            }
        })


        return v
    }
}