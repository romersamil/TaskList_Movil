package com.example.tasklist_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        goTask()
        goEdit()
    }

    private fun goTask(){
        val button = findViewById<Button>(R.id.taskbtn)
        button.setOnClickListener{
            val go = Intent(this, TaskCreationActivity::class.java)
            startActivity(go)
        }

    }

    private fun goEdit(){
        val buton = findViewById<Button>(R.id.editTaskbtn)
        buton.setOnClickListener{
            val go =  Intent(this, EditTaskActivity::class.java)
            startActivity(go)
        }
    }
}