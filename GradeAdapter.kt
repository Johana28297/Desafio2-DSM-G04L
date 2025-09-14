package com.example.desafio02

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio02.models.Grade

class GradeAdapter(private val grades: List<Grade>, private val context: Context) :
    RecyclerView.Adapter<GradeAdapter.GradeViewHolder>() {

    class GradeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val studentNameTv: TextView = view.findViewById(R.id.grade_student_name_tv)
        val gradeDetailsTv: TextView = view.findViewById(R.id.grade_details_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grade, parent, false)
        return GradeViewHolder(view)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val grade = grades[position]
        holder.studentNameTv.text = grade.studentName
        holder.gradeDetailsTv.text = "Grado: ${grade.grade}, Materia: ${grade.subject}, Nota: ${grade.finalGrade}"

        // ✅ Al tocar una nota, abrir EditGradeActivity con el ID
        holder.itemView.setOnClickListener {
            val intent = Intent(context, EditGradeActivity::class.java)
            intent.putExtra("gradeId", grade.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = grades.size
}