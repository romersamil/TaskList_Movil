package com.example.tasklist_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth


enum class ProviderType {
    BASIC
}
class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvGoRegister = findViewById<TextView>(R.id.tv_go_to_register)
        tvGoRegister.setOnClickListener {
            goToRegister()
        }

        val tvgoRestore = findViewById<TextView>(R.id.GoRestore)
        tvgoRestore.setOnClickListener { goTorestore() }

       // login()
    }

    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    /*private fun login() {
        val enter_btn = findViewById<Button>(R.id.enter_btn)
        val Name_Edit_Text = findViewById<EditText>(R.id.Name_Edit_Text)
        val Email_Edit_Text = findViewById<EditText>(R.id.Email_Edit_Text)
        val Password_Edit_Text = findViewById<EditText>(R.id.Password_Edit_Text)
        val Confir_Password_Edit_Text = findViewById<EditText>(R.id.Confir_Password_Edit_Text)
        enter_btn.setOnClickListener {
            if (Name_Edit_Text.text.isNotEmpty() && Email_Edit_Text.text.isNotEmpty() && Password_Edit_Text.text.isNotEmpty()
                && Confir_Password_Edit_Text.text.isNotEmpty()
            ) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        Email_Edit_Text.text.toString(),
                        Password_Edit_Text.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)

                        } else {
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("SE HA PRODUCIDO UN ERROR AL REGISTRAR EL USUARIO")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent : Intent = Intent( this, Login::class.java).apply{
            putExtra("email", email)
            putExtra( "provider", provider.name )
        }
        startActivity(homeIntent)
    }*/

    private fun goTorestore(){
        val i = Intent(this, RestoreActivity::class.java)
        startActivity(i)
    }
}