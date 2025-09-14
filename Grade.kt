package com.example.desafio02.models

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Grade(
    val studentName: String = "",
    val grade: String = "",
    val subject: String = "",
    val finalGrade: Double = 0.0
) {
    @get:Exclude
    var id: String = ""
}