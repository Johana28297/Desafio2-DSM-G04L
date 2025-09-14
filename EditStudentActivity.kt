package com.example.desafio02

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.desafio02.models.Student
import com.google.firebase.firestore.FirebaseFirestore

// 🔄 Ahora hereda de BaseActivity
class EditStudentActivity : BaseActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var studentId: String

    private lateinit var nameEt: EditText
    private lateinit var ageEt: EditText
    private lateinit var addressEt: EditText
    private lateinit var phoneEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        firestore = FirebaseFirestore.getInstance()
        studentId = intent.getStringExtra("studentId") ?: return finish()

        nameEt = findViewById(R.id.edit_name_et)
        ageEt = findViewById(R.id.edit_age_et)
        addressEt = findViewById(R.id.edit_address_et)
        phoneEt = findViewById(R.id.edit_phone_et)

        val updateBtn = findViewById<Button>(R.id.update_student_btn)
        val deleteBtn = findViewById<Button>(R.id.delete_student_btn)

        loadStudentData()

        updateBtn.setOnClickListener {
            updateStudent()
        }

        deleteBtn.setOnClickListener {
            confirmDeleteStudent()
        }
    }

    private fun loadStudentData() {
        firestore.collection("students").document(studentId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val student = document.toObject(Student::class.java)
                    if (student != null) {
                        nameEt.setText(student.name)
                        ageEt.setText(student.age.toString())
                        addressEt.setText(student.address)
                        phoneEt.setText(student.phone)
                    }
                } else {
                    Toast.makeText(this, getString(R.string.student_not_found), Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, getString(R.string.load_error, e.message), Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateStudent() {
        val name = nameEt.text.toString().trim()
        val ageStr = ageEt.text.toString().trim()
        val address = addressEt.text.toString().trim()
        val phone = phoneEt.text.toString().trim()

        if (name.isEmpty() || ageStr.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

        val ageInt = ageStr.toIntOrNull()
        if (ageInt == null || ageInt <= 0) {
            Toast.makeText(this, getString(R.string.invalid_age), Toast.LENGTH_SHORT).show()
            return
        }

        val updatedStudent = mapOf(
            "name" to name,
            "age" to ageInt,
            "address" to address,
            "phone" to phone
        )

        firestore.collection("students").document(studentId).update(updatedStudent)
            .addOnSuccessListener {
                Toast.makeText(this, getString(R.string.update_success), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, getString(R.string.update_error, e.message), Toast.LENGTH_SHORT).show()
            }
    }

    private fun confirmDeleteStudent() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.delete_student_title))
            .setMessage(getString(R.string.delete_student_message))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                deleteStudent()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun deleteStudent() {
        firestore.collection("students").document(studentId).delete()
            .addOnSuccessListener {
                Toast.makeText(this, getString(R.string.delete_success), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, getString(R.string.delete_error, e.message), Toast.LENGTH_SHORT).show()
            }
    }
}