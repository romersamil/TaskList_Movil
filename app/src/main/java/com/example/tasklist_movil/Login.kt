package com.example.tasklist_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvGoRegister = findViewById<TextView>(R.id.tv_go_to_register)
        tvGoRegister.setOnClickListener{
            goToRegister()
        }

        val tvgoRestore = findViewById<TextView>(R.id.GoRestore)
        tvgoRestore.setOnClickListener{goTorestore()}
    }
    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    private fun goTorestore(){
        val i = Intent(this, RestoreActivity::class.java)
        startActivity(i)
    }
}