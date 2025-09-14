package com.example.desafio02.models

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Student(
    val name: String = "",
    val age: Int = 0,
    val address: String = "",
    val phone: String = ""
) {
    @get:Exclude
    var id: String = ""
}