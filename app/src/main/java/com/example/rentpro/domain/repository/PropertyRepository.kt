package com.example.rentpro.domain.repository

import com.example.rentpro.domain.model.Property
import com.example.rentpro.utils.Resource
import com.google.firebase.firestore.DocumentReference

interface PropertyRepository {
    suspend fun getAllProperties(): Resource<List<Property>>
    suspend fun searchProperty(keyword: String): Resource<List<Property>>
    suspend fun getFavouriteProperty(references: List<DocumentReference>): Resource<List<Property>>
}