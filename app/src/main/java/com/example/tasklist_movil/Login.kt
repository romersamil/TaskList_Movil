package com.example.tasklist_movil

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tasklist_movil.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            val email = binding.coreoRegis.text.toString()
            val pass = binding.passRegis.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                    } else {
                        //Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        val ErroralCampo = findViewById<TextView>(R.id.ErrorAlIniciar)
                        ErroralCampo.visibility = View.VISIBLE

                        val fadeInAnimation = AlphaAnimation(0f, 1f)
                        fadeInAnimation.duration = 1000 // Duración de la animación en milisegundos
                        ErroralCampo.startAnimation(fadeInAnimation)

                        ErroralCampo.postDelayed({
                            ErroralCampo.visibility = View.GONE
                        }, 3000)
                    }
                }
            } else {
                //Toast.makeText(this, "HAY CAMPOS VACIOS", Toast.LENGTH_SHORT).show()
                val camposerror = findViewById<TextView>(R.id.ErrorCampos)
                camposerror.visibility = View.VISIBLE

                val fadeInAnimation = AlphaAnimation(0f, 1f)
                fadeInAnimation.duration = 1000 // Duración de la animación en milisegundos
                camposerror.startAnimation(fadeInAnimation)

                camposerror.postDelayed({
                    camposerror.visibility = View.GONE
                }, 3000)
            }
        }

        binding.tvGoToRegister.setOnClickListener {
            goToRegister()
        }

        binding.GoRestore.setOnClickListener {
            goToRestore()
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun goToRestore() {
        val intent = Intent(this, RestoreActivity::class.java)
        startActivity(intent)
    }
}
