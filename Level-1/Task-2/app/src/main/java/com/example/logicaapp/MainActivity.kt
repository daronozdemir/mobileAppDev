package com.example.logicaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnConfirm.setOnClickListener{
            checkAnswer()
        }
    }

    private fun checkAnswer() {
        var count = 0
        val answer1 = answer1.text.toString()
        val answer2 = answer2.text.toString()
        val answer3 = answer3.text.toString()
        val answer4 = answer4.text.toString()
        if (answer1 == "T" || answer1 == "t"){
            count++
        }
        if (answer2 == "F" ||answer2 == "f"){
            count++
        }
        if (answer3 == "F" ||answer3 == "f"){
            count++
        }
        if (answer4 == "F" || answer4 == "f"){
            count++
        }

        Toast.makeText(this, getString(R.string.correct)+" "+ count.toString(), Toast.LENGTH_LONG).show()

    }
}
