package com.example.desafio02

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio02.models.Student
import com.google.firebase.firestore.FirebaseFirestore

// 🔄 Ahora hereda de BaseActivity
class StudentListActivity : BaseActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        firestore = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.student_list_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadStudents()
    }

    private fun loadStudents() {
        firestore.collection("students").get()
            .addOnSuccessListener { result ->
                val students = mutableListOf<Student>()
                for (document in result) {
                    val student = document.toObject(Student::class.java)
                    student.id = document.id // Guarda el ID del documento
                    students.add(student)
                }
                adapter = StudentAdapter(students, this) // ✅ Pasar el contexto
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al cargar estudiantes: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}