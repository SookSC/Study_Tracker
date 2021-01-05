package com.example.study_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File
import java.io.FileInputStream

class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        val SD_main = getExternalFilesDir("/StudyTimerData")

        var createSuccess = true

        // Create directory if it does not already exist
        if(!SD_main!!.exists()) {
            createSuccess = SD_main.mkdir()
        }

        // Directory already exists or creation of directory is successful
        if(createSuccess) {
            val logFile = File("StudyTimerLogData.txt")

            // Create text file if it does not already exist
            if(!logFile.exists()){
                createSuccess = logFile.mkdir()
            }

            // Text file already exists or creation of text file is successful
            if(createSuccess) {
                // Directory and text file exist!
                // For testing:
                Toast.makeText(this@LogActivity,"Log directory and text file exist",
                    Toast.LENGTH_SHORT).show()
            }

            // Text file creation not successful
            else {
                // For testing:
                Toast.makeText(this@LogActivity,"Text file creation UNSUCCESSFUL",
                    Toast.LENGTH_SHORT).show()
            }
        }

        // Directory creation not successful
        else {
            Toast.makeText(this@LogActivity,"Directory creation UNSUCCESSFUL",
                Toast.LENGTH_SHORT).show()
        }

        // Bottom navigation bar
        var intentHome = Intent(this, HomeActivity::class.java)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                // TODO: when returning to home page, if timers were running, return to that
                //  activity instead of opening a new home activity.
                //  Or just return to existing home activity in general instead of creating a new
                //  one.
                R.id.ic_home -> startActivity(intentHome)
                //Since already on log page, clicking log icon does nothing
                R.id.ic_log -> null
            }
            true
        }

        /*
        val path = getExternalFilesDir(null)

        val studyTimerDirectory = File(path, "StudyTimer")
        studyTimerDirectory.mkdirs()
        val file = File(studyTimerDirectory, "StudyTimerLogData.txt")

        //Write
        file.appendText("Record goes here")

        //Read
        val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }
         */




        /*
        val fileName = "StudyTimerLogData.txt"
        
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

         */
    }
}