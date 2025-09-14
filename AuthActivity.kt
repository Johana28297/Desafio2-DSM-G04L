package com.example.desafio02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        auth = Firebase.auth
        val emailEt = findViewById<EditText>(R.id.email_et)
        val passwordEt = findViewById<EditText>(R.id.password_et)
        val authBtn = findViewById<Button>(R.id.auth_btn)
        val switchTv = findViewById<TextView>(R.id.switch_auth_tv)
        val titleTv = findViewById<TextView>(R.id.auth_title_tv)

        // Checks if the user is already logged in
        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // Handle the authentication button click
        authBtn.setOnClickListener {
            val email = emailEt.text.toString()
            val password = passwordEt.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isLoginMode) {
                // Login mode
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(baseContext, "Fallo de autenticación: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Registration mode
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(baseContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
                            // Automatically signs in the new user and navigates to the next screen
                            auth.signInWithEmailAndPassword(email, password)
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(baseContext, "Fallo de registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        // Switch between login and registration mode
        switchTv.setOnClickListener {
            isLoginMode = !isLoginMode
            if (isLoginMode) {
                authBtn.text = getString(R.string.login_button)
                switchTv.text = getString(R.string.switch_to_register)
                titleTv.text = getString(R.string.login_button)
            } else {
                authBtn.text = getString(R.string.register_button)
                switchTv.text = getString(R.string.switch_to_login)
                titleTv.text = getString(R.string.register_button)
            }
        }
    }
}
