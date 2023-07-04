package com.example.rentpro.domain.model

import com.google.firebase.firestore.DocumentReference

data class User(
    val fullName: String,
    val email: String,
    val contactNum: String,
    val userType: String,
    val identificationNum: String,
    val imagePath: String,
    val favourites: List<DocumentReference> = emptyList()
){
    constructor(): this("", "", "", "", "", "", emptyList())
}
