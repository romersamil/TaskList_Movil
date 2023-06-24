package com.example.tasklist_movil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tasklist_movil.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.jar.Attributes.Name


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvGoToLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        binding.Registrarsebtn.setOnClickListener{
            val name = binding.NameEditText.text.toString()
            val email = binding.EmailEditText.text.toString()
            val pass = binding.PasswordEditText.text.toString()
            val confirmPass = binding.ConfirPasswordEditText.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if (pass == (confirmPass)){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this , Login::class.java)
                            startActivity(intent)
                        }else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }

                }else{
                    Toast.makeText(this, "LAS CONTRASENA NO ES IGUAL A LA CONFIRMACION", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "HAY CAMPOS VACIOS", Toast.LENGTH_SHORT).show()

            }
        }
        val tvGoLogin = findViewById<TextView>(R.id.tv_go_to_login)
        tvGoLogin.setOnClickListener{
            goToLogin()


        }
    }



    private fun goToLogin() {
        val i = Intent(this, Login::class.java)
        startActivity(i)
    }
}