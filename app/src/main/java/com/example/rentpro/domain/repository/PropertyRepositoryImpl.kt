package com.example.rentpro.domain.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.rentpro.domain.model.Property
import com.example.rentpro.utils.Resource
import com.example.rentpro.utils.await
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
): PropertyRepository {
    override suspend fun getAllProperties(): Resource<List<Property>>
    {
        return try {
            val properties = firestore.collection("properties").get().await()
            val propertyList = properties.documents.mapNotNull  { document ->
                document.toObject<Property>()?.copy(id = document.id)
            }
            Resource.Success(propertyList)
        } catch(e: Exception){
                e.printStackTrace()
                Resource.Error(e.message.toString())
            }
    }

    override suspend fun searchProperty(keyword: String): Resource<List<Property>> {
        return try {
            val querySnapshot = firestore.collection("properties")
                .get()
                .await()

            val propertyList = querySnapshot.documents.mapNotNull { document ->
                val property = document.toObject<Property>()
                if (property != null &&
                    (property.title.equals(keyword, ignoreCase = true) ||
                            property.rent.toString().equals(keyword, ignoreCase = true) ||
                            property.numBathroom.toString().equals(keyword, ignoreCase = true) ||
                            property.numBedroom.toString().equals(keyword, ignoreCase = true) ||
                            property.location.equals(keyword, ignoreCase = true))
                ) {
                    property.copy(id = document.id)
                } else {
                    null
                }
            }
            Resource.Success(propertyList)
        } catch(e: Exception){
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getFavouriteProperty(references: List<DocumentReference>): Resource<List<Property>> {
        return try {
            val propertyList = mutableListOf<Property>()
            for (reference in references) {
                val documentSnapshot = reference.get().await()
                val property = documentSnapshot.toObject<Property>()
                if (property != null) {
                    propertyList.add(property.copy(id = documentSnapshot.id))
                } else {
                    Log.e(TAG, "Failed to retrieve favorite property")
                }
            }
            Resource.Success(propertyList)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }
}