package com.example.study_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_home.view.*
//import kotlinx.android.synthetic.main.activity_home.view.breakButton
import kotlinx.android.synthetic.main.fragment_break_before.*
import kotlinx.android.synthetic.main.fragment_break_before.view.*

private lateinit var viewOfLayout: View

class BreakBeforeFragment : Fragment() {
    lateinit var comm: Communicator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_break_before, container, false)

        comm = activity as Communicator

        v.breakButton.setOnClickListener {
            comm.passDataCom(v.editTextBreakNotes.text.toString())
        }

        /*
        val breakButton = v.findViewById<Button>(R.id.breakButton)

        breakButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var userNotes = editTextBreakNotes.text.toString()

                val intent = Intent(activity, HomeActivity::class.java)
                intent.putExtra(userNotes, editTextBreakNotes)

                startActivity(intent);
                }

            }
        })
        */

        return v
    }
}