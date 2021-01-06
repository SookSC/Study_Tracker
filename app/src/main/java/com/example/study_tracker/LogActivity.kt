package com.example.study_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_break_during.view.*
import java.io.*

class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        var filename = "LogData.txt"

        var path = getExternalFilesDir(null)

        var fileOut = File(path, filename)

        var success = true
        if(!fileOut.exists()){
            success = fileOut.createNewFile()
        }

        if(success){
            Toast.makeText(this@LogActivity, "File successfully exists!",
            Toast.LENGTH_SHORT).show()

            fileOut.appendText("This is what I'm printing")
            var inputStream: InputStream = fileOut.inputStream()
            var allText = inputStream.bufferedReader().use(BufferedReader::readText)

            val displayText = findViewById<TextView>(R.id.readLogText)

            displayText.setText(allText)

        }
        else{
            Toast.makeText(this@LogActivity, "File creation UNSUCCESSFUL",
                    Toast.LENGTH_SHORT).show()
        }

/*
        // -------- RETRIEVE AND DISPLAY LOG DATA -------- //
        /*
        var intent = intent
        var textBreakNote = intent.getStringExtra("textInputBreakNotes")*/


        // -------- CREATE DIRECTORY AND TEXT FILE IF IT DOESN'T ALREADY EXIST -------- //
        val SD_main = getExternalFilesDir("/StudyTimerLogData")

        var createSuccess = true

        // Create directory if it does not already exist
        if (!SD_main!!.exists()) {
            createSuccess = SD_main.mkdir()
        }

        val logFile = File(SD_main, "LogData.txt")

        // Directory already exists or creation of directory is successful
        if (createSuccess) {

            // Create text file if it does not already exist
            if (!logFile.exists()){
                createSuccess = logFile.mkdir()
                Toast.makeText(this@LogActivity, "New file created",
                        Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this@LogActivity, "NO new file created",
                        Toast.LENGTH_SHORT).show()
            }
            // Text file already exists or creation of text file is successful
            if (createSuccess) {
                // Directory and text file exist!
                Toast.makeText(this@LogActivity, "Log directory and text file exist",
                        Toast.LENGTH_SHORT).show()

                /*
                logFile.appendText("Yes! This is finally displaying!")
                logFile.appendText("/n")
                val inputStream: InputStream = File("LogData.txt").inputStream()
                val inputString = inputStream.bufferedReader().use { it.readText() }

                val displayText = findViewById<TextView>(R.id.readLogText)

                displayText.setText(inputString)
*/
                // Write to text file
                /*
                val dest = File(logFile, "Name")
                var holder = "This is the text"
                try {
                    PrintWriter(dest).use { out -> out.println(holder) }
                } catch (e: Exception) {
                    // handle the exception
                    Toast.makeText(this@LogActivity, "Exception thrown",
                            Toast.LENGTH_SHORT).show()
                } */
            }

            // Text file creation not successful
            else {
                Toast.makeText(this@LogActivity, "Text file creation UNSUCCESSFUL",
                        Toast.LENGTH_SHORT).show()
            }
        }

        // Directory creation not successful
        else {
            Toast.makeText(this@LogActivity,"Directory creation UNSUCCESSFUL",
                Toast.LENGTH_SHORT).show()
        }
*/
        // -------- BOTTOM NAVIGATION BAR -------- //
        var intentHome = Intent(this, HomeActivity::class.java)
        intentHome.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT

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


    }
}