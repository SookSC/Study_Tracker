package com.example.study_tracker

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_end_session.view.*

private lateinit var viewOfLayout: View

class EndSessionFragment : Fragment() {

    var elapsedTimeMin: Int? = 0
    var elapsedTimeSec: Int? = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_end_session, container, false)
        elapsedTimeMin = arguments?.getInt("elapsedTimeMin")
        elapsedTimeSec = arguments?.getInt("elapsedTimeSec")

        if (elapsedTimeSec!! < 10) {
            view.textStudyTime.setText("$elapsedTimeMin" + ":" + "0" + "$elapsedTimeSec")
        }

        else {
            view.textStudyTime.setText("$elapsedTimeMin" + ":" + "$elapsedTimeSec")
        }

        return view
    }
}