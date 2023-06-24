package com.example.tasklist_movil

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tasklist_movil.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth



class Login : AppCompatActivity() {



    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.GoRestore.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener{

                val email = binding.coreoRegis.text.toString()
                val pass = binding.passRegis.text.toString()

                if (email.isNotEmpty() && pass.isNotEmpty()){
                        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful){
                                val intent = Intent(this , MainActivity::class.java)
                                startActivity(intent)
                            }else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                            }
                        }
                }else {
                    Toast.makeText(this, "HAY CAMPOS VACIOS", Toast.LENGTH_SHORT).show()

                }
            }

        val tvGoRegister = findViewById<TextView>(R.id.tv_go_to_register)
        tvGoRegister.setOnClickListener {
            goToRegister()
        }

        val tvgoRestore = findViewById<TextView>(R.id.GoRestore)
        tvgoRestore.setOnClickListener { goTorestore() }

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