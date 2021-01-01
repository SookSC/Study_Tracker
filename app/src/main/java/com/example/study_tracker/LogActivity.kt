package com.example.study_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.File

class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        val fileName = "StudyTrackerLogData.txt"
        
        var file = File(fileName)

        var fileExists = file.exists()

        if(fileExists){
            //Do nothing
        }
        else {
            //Create file
        }

        //Open file
        //Read/write to file
    }
}