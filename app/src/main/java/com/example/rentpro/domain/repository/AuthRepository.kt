package com.example.rentpro.domain.repository

import com.example.rentpro.domain.model.User
import com.example.rentpro.utils.Resource
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun register(fullName: String, email: String, password: String): Resource<FirebaseUser>
    suspend fun update(fullName: String, contactNum: String, identificationNum: String, newPassword: String):Resource<FirebaseUser>
    fun logout()

    suspend fun getFavourites(): Resource<List<DocumentReference>>
    suspend fun getUser(): Resource<User>
}