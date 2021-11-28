package com.example.numbersgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var myRV: RecyclerView
    private lateinit var submitButton: Button
    private lateinit var messageText: TextView
    private lateinit var clMain: ConstraintLayout

    private val messages = ArrayList<String>()
    private var count = 1
    private var answer = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRV = findViewById<RecyclerView>(R.id.rvMain)
        submitButton = findViewById<Button>(R.id.submitButton)
        messageText = findViewById<TextView>(R.id.messageText)
        clMain = findViewById(R.id.clMain)
        myRV.adapter = RecyclerViewAdapter(messages)
        myRV.layoutManager = LinearLayoutManager(this)

        answer = Random.nextInt(0, 11)
        submitButton.setOnClickListener { AddTOList(answer) }
    }

    private fun AddTOList(answer: Int) {
        var message = messageText.text.toString()

        if (message.isNotEmpty()) {
            try {
                if(count < 4){

                    if(message.toInt() == answer){
                        submitButton.isEnabled = false
                        submitButton.isClickable = false
                        messages.add("-----------------------------------------")
                        messages.add("You win!\nThe correct answer was $answer\nGame Over")
                    }else{
                        count++
                        messages.add("You guessed $message\nYou have $count guesses left")
                    }

                    if(count == 4){
                        submitButton.isEnabled = false
                        submitButton.isClickable = false
                        messages.add("-----------------------------------------")
                        messages.add("You lose\nThe correct answer was $answer\nGame Over")
                    }
                }
                messageText.text= ""
                myRV.adapter?.notifyDataSetChanged()

            } catch (e: Exception) {
                Snackbar.make(clMain, "Please enter number only", Snackbar.LENGTH_LONG).show()
            }
        } else {
            Snackbar.make(clMain, "Please enter some text", Snackbar.LENGTH_LONG).show()
        }
    }
}