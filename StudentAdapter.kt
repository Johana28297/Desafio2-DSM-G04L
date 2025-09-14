package com.example.desafio02

import android.widget.Toast
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio02.models.Student

class StudentAdapter(private val students: List<Student>, private val context: Context) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTv: TextView = view.findViewById(R.id.student_name_tv)
        val detailsTv: TextView = view.findViewById(R.id.student_details_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameTv.text = student.name
        holder.detailsTv.text = "Edad: ${student.age}, Dirección: ${student.address}, Teléfono: ${student.phone}"

        holder.itemView.setOnClickListener {
            Log.d("StudentAdapter", "ID del estudiante: ${student.id}") // 🐞 Log para verificar

            if (student.id.isBlank()) {
                // 🛑 Si el ID está vacío, mostrar mensaje y no abrir la actividad
                Toast.makeText(context, "ID del estudiante no disponible", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(context, EditStudentActivity::class.java)
            intent.putExtra("studentId", student.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = students.size
}