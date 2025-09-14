package com.example.desafio02

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.desafio02.models.Student
import com.google.firebase.firestore.FirebaseFirestore

// 🔄 Ahora hereda de BaseActivity
class RegisterStudentActivity : BaseActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_student)

        firestore = FirebaseFirestore.getInstance()

        val nameEt = findViewById<EditText>(R.id.name_et)
        val ageEt = findViewById<EditText>(R.id.age_et)
        val addressEt = findViewById<EditText>(R.id.address_et)
        val phoneEt = findViewById<EditText>(R.id.phone_et)
        val saveBtn = findViewById<Button>(R.id.save_student_btn)

        saveBtn.setOnClickListener {
            val name = nameEt.text.toString()
            val age = ageEt.text.toString()
            val address = addressEt.text.toString()
            val phone = phoneEt.text.toString()

            if (name.isEmpty() || age.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val student = Student(name, age.toInt(), address, phone)
            firestore.collection("students").add(student)
                .addOnSuccessListener {
                    Toast.makeText(this, "Estudiante registrado exitosamente", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al registrar estudiante: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}