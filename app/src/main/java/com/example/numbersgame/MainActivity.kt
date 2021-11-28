package com.example.numbersgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var myRV: RecyclerView
    private lateinit var submitButton: Button
    private lateinit var messageText: TextView
    private val messages = arrayListOf<String>()
    private lateinit var clMain: ConstraintLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRV = findViewById<RecyclerView>(R.id.rvMain)
        submitButton = findViewById<Button>(R.id.submitButton)
        messageText = findViewById<TextView>(R.id.messageText)
        clMain = findViewById(R.id.clMain)

        myRV.adapter = RecyclerViewAdapter(messages)
        myRV.layoutManager = LinearLayoutManager(this)

        submitButton.setOnClickListener { AddTOList() }
    }

    private fun AddTOList(){
        var message = messageText.text.toString()

        if(message.isNotEmpty()){
            messages.add(message)
            myRV.adapter!!.notifyDataSetChanged()
            messageText.text = ""
        }else{
            Snackbar.make(clMain, "Please enter some text", Snackbar.LENGTH_LONG).show()
        }
    }
}