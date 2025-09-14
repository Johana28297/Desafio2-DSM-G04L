package com.example.desafio02

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.desafio02.models.Grade
import com.google.firebase.firestore.FirebaseFirestore

// 🔄 Ahora hereda de BaseActivity
class EditGradeActivity : BaseActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var gradeId: String

    private lateinit var studentNameEt: EditText
    private lateinit var gradeEt: EditText
    private lateinit var subjectEt: EditText
    private lateinit var finalGradeEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_grade)

        firestore = FirebaseFirestore.getInstance()
        gradeId = intent.getStringExtra("gradeId") ?: return finish()

        studentNameEt = findViewById(R.id.edit_student_name_et)
        gradeEt = findViewById(R.id.edit_grade_et)
        subjectEt = findViewById(R.id.edit_subject_et)
        finalGradeEt = findViewById(R.id.edit_final_grade_et)

        val updateBtn = findViewById<Button>(R.id.update_grade_btn)
        val deleteBtn = findViewById<Button>(R.id.delete_grade_btn)

        loadGradeData()

        updateBtn.setOnClickListener {
            updateGrade()
        }

        deleteBtn.setOnClickListener {
            confirmDeleteGrade()
        }
    }

    private fun loadGradeData() {
        firestore.collection("grades").document(gradeId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val grade = document.toObject(Grade::class.java)
                    if (grade != null) {
                        studentNameEt.setText(grade.studentName)
                        gradeEt.setText(grade.grade)
                        subjectEt.setText(grade.subject)
                        finalGradeEt.setText(grade.finalGrade.toString())
                    }
                } else {
                    Toast.makeText(this, getString(R.string.grade_not_found), Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, getString(R.string.load_error, e.message), Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateGrade() {
        val studentName = studentNameEt.text.toString().trim()
        val gradeText = gradeEt.text.toString().trim()
        val subject = subjectEt.text.toString().trim()
        val finalGradeStr = finalGradeEt.text.toString().trim()

        if (studentName.isEmpty() || gradeText.isEmpty() || subject.isEmpty() || finalGradeStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

        val finalGradeValue = finalGradeStr.toDoubleOrNull()
        if (finalGradeValue == null || finalGradeValue < 0.0 || finalGradeValue > 10.0) {
            Toast.makeText(this, getString(R.string.invalid_final_grade), Toast.LENGTH_SHORT).show()
            return
        }

        val updatedGrade = mapOf(
            "studentName" to studentName,
            "grade" to gradeText,
            "subject" to subject,
            "finalGrade" to finalGradeValue
        )

        firestore.collection("grades").document(gradeId).update(updatedGrade)
            .addOnSuccessListener {
                Toast.makeText(this, getString(R.string.update_success), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, getString(R.string.update_error, e.message), Toast.LENGTH_SHORT).show()
            }
    }

    private fun confirmDeleteGrade() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.delete_grade_title))
            .setMessage(getString(R.string.delete_grade_message))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                deleteGrade()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun deleteGrade() {
        firestore.collection("grades").document(gradeId).delete()
            .addOnSuccessListener {
                Toast.makeText(this, getString(R.string.delete_success), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, getString(R.string.delete_error, e.message), Toast.LENGTH_SHORT).show()
            }
    }
}