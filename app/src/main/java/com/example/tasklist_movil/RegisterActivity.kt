package com.example.tasklist_movil

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        val tvGoLogin = findViewById<TextView>(R.id.tv_go_to_login)
        tvGoLogin.setOnClickListener{
            goToLogin()

            // configuration
            config()
        }
    }

    private fun config() {
        val Registrarse_btn = findViewById<Button>(R.id.Registrarse_btn)
        val Name_Edit_Text = findViewById<EditText>(R.id.Name_Edit_Text)
        val Email_Edit_Text = findViewById<EditText>(R.id.Email_Edit_Text)
        val Password_Edit_Text = findViewById<EditText>(R.id.Password_Edit_Text)
        val Confir_Password_Edit_Text = findViewById<EditText>(R.id.Confir_Password_Edit_Text)
        title = "Validacion"
        Registrarse_btn.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Adiós")
                .setCancelable(false)
                .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->
                    // Acción a realizar al pulsar el botón "Aceptar" en el cuadro de diálogo
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Mensaje")
            alert.show()

           /* if (Name_Edit_Text.text.isNotEmpty() && Email_Edit_Text.text.isNotEmpty() && Password_Edit_Text.text.isNotEmpty()
                && Confir_Password_Edit_Text.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(Email_Edit_Text.text.toString(),
                        Password_Edit_Text.text.toString()).addOnCompleteListener {
                            if (it.isSuccessful) {
                                showHome(it.result?.user?.email ?: "", ProviderType.BASIC)

                            } else {
                                showAlert()
                            }
                    }
            }*/
        }
    }

   /*private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("SE HA PRODUCIDO UN ERROR AL REGISTRAR EL USUARIO")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }*/

    /*private fun showHome(email: String, provider: ProviderType){
        val homeIntent : Intent = Intent( this, Login::class.java).apply{
            putExtra("email", email)
            putExtra( "provider", provider.name )
        }
        startActivity(homeIntent)
    }*/
    private fun goToLogin() {
        val i = Intent(this, Login::class.java)
        startActivity(i)
    }
}