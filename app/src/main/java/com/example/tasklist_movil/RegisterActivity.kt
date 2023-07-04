package com.example.tasklist_movil

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tasklist_movil.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var firebaseAuth: FirebaseAuth

    private val messageLog = mutableListOf<String>()
    fun logMessage(message: String) {
        messageLog.add(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvGoToLogin.setOnClickListener {
            goToLogin()
        }

        binding.Registrarsebtn.setOnClickListener {
            val name = binding.NameEditText.text.toString()
            val email = binding.EmailEditText.text.toString()
            val pass = binding.PasswordEditText.text.toString()
            val confirmPass = binding.ConfirPasswordEditText.text.toString()

            registerUser(name, email, pass, confirmPass)
        }

    }

    fun registerUser(name: String, email: String, pass: String, confirmPass: String) {
        if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
            if (pass == confirmPass) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Error al registrar el usuario: ${task.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                val errorTextView = findViewById<TextView>(R.id.errorTextView)
                errorTextView.visibility = View.VISIBLE

                val fadeInAnimation = AlphaAnimation(0f, 1f)
                fadeInAnimation.duration = 500 // Duración de la animación en milisegundos
                errorTextView.startAnimation(fadeInAnimation)

                Handler().postDelayed({
                    errorTextView.visibility = View.GONE
                }, 3000)
            }
        } else {
            val errorblanco = findViewById<TextView>(R.id.ErrorBlanco)
            errorblanco.visibility = View.VISIBLE

            val fadeInAnimation = AlphaAnimation(0f, 1f)
            fadeInAnimation.duration = 1000 // Duración de la animación en milisegundos
            errorblanco.startAnimation(fadeInAnimation)

            Handler().postDelayed({
                errorblanco.visibility = View.GONE
            }, 3000)
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }


}