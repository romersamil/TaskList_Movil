package com.example.tasklist_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EditTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)
        GoExit()
    }

    private fun GoExit(){
        val button = findViewById<Button>(R.id.salir)
        button.setOnClickListener {
            val go = Intent(this, Home::class.java)
            startActivity(go)
        }
    }
}