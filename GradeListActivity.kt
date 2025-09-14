package com.example.desafio02

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio02.models.Grade
import com.google.firebase.firestore.FirebaseFirestore

// 🔄 Ahora hereda de BaseActivity
class GradeListActivity : BaseActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GradeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade_list)

        firestore = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.grade_list_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadGrades()
    }

    private fun loadGrades() {
        firestore.collection("grades").get()
            .addOnSuccessListener { result ->
                val grades = mutableListOf<Grade>()
                for (document in result) {
                    val grade = document.toObject(Grade::class.java)
                    grade.id = document.id // ✅ Asignar el ID del documento Firestore
                    grades.add(grade)
                }
                adapter = GradeAdapter(grades, this) // ✅ Pasar el contexto
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al cargar notas: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}