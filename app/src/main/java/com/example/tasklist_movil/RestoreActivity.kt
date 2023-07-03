package com.example.tasklist_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RestoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore)

        val goBack = findViewById<TextView>(R.id.tv_go_to_register)
        goBack.setOnClickListener{goback()}
    }


    private fun goback(){
        val i = Intent(this, Login::class.java)
        startActivity(i)
    }
}


