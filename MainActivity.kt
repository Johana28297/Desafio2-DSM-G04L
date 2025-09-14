package com.example.desafio02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Verifica si el usuario está autenticado
        val auth = Firebase.auth
        if (auth.currentUser == null) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_register_student).setOnClickListener {
            startActivity(Intent(this, RegisterStudentActivity::class.java))
        }

        findViewById<Button>(R.id.btn_register_grade).setOnClickListener {
            startActivity(Intent(this, RegisterGradeActivity::class.java))
        }

        findViewById<Button>(R.id.btn_view_students).setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }

        findViewById<Button>(R.id.btn_view_grades).setOnClickListener {
            startActivity(Intent(this, GradeListActivity::class.java))
        }

        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}
