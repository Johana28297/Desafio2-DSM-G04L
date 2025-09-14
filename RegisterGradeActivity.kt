package com.example.desafio02

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.desafio02.models.Grade
import com.example.desafio02.models.Student
import com.google.firebase.firestore.FirebaseFirestore

// 🔄 Ahora hereda de BaseActivity
class RegisterGradeActivity : BaseActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var studentSpinner: Spinner
    private lateinit var gradeSpinner: Spinner
    private lateinit var subjectSpinner: Spinner
    private lateinit var finalGradeEt: EditText

    private var students = mutableListOf<Student>()
    private val grades = listOf("1°", "2°", "3°", "4°", "5°")
    private val subjects = listOf("Matemáticas", "Ciencias", "Historia", "Lenguaje")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_grade)

        firestore = FirebaseFirestore.getInstance()

        studentSpinner = findViewById(R.id.student_spinner)
        gradeSpinner = findViewById(R.id.grade_spinner)
        subjectSpinner = findViewById(R.id.subject_spinner)
        finalGradeEt = findViewById(R.id.final_grade_et)
        val saveBtn = findViewById<Button>(R.id.save_grade_btn)

        // Configura los Spinners
        val gradeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, grades)
        gradeSpinner.adapter = gradeAdapter

        val subjectAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, subjects)
        subjectSpinner.adapter = subjectAdapter

        // Carga los estudiantes
        loadStudents()

        saveBtn.setOnClickListener {
            val selectedStudent = studentSpinner.selectedItem.toString()
            val selectedGrade = gradeSpinner.selectedItem.toString()
            val selectedSubject = subjectSpinner.selectedItem.toString()
            val finalGradeText = finalGradeEt.text.toString()

            if (finalGradeText.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa la nota", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val finalGrade = finalGradeText.toDouble()
            if (finalGrade < 0 || finalGrade > 10) {
                Toast.makeText(this, "La nota debe estar entre 0 y 10", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val grade = Grade(selectedStudent, selectedGrade, selectedSubject, finalGrade)
            firestore.collection("grades").add(grade)
                .addOnSuccessListener {
                    Toast.makeText(this, "Nota registrada exitosamente", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al registrar nota: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun loadStudents() {
        firestore.collection("students").get()
            .addOnSuccessListener { result ->
                students.clear()
                val studentNames = mutableListOf<String>()
                for (document in result) {
                    val student = document.toObject(Student::class.java)
                    students.add(student)
                    studentNames.add(student.name)
                }
                val studentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, studentNames)
                studentSpinner.adapter = studentAdapter
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al cargar estudiantes: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}