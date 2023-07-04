package com.example.rentpro.domain.repository

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.example.rentpro.domain.model.User
import com.example.rentpro.utils.Resource
import com.example.rentpro.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal suspend fun loginInternal(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user

            Resource.Success(user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal suspend fun registerInternal(fullName: String, email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user

            Resource.Success(user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try{
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user

            // Check userType
            val userDocument = firestore.collection("users").document(user?.uid.toString())
            val documentSnapshot = userDocument.get().await()

            if(documentSnapshot.exists())
            {
                val userType = documentSnapshot.getString("userType")

                if (userType == "CUSTOMER") {
                    Resource.Success(user!!)
                } else {
                    Resource.Error("ADMIN")
                }
            } else {
                Resource.Error("User Not Registered!")
            }
        } catch(e: Exception){
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun register(fullName: String, email: String, password: String): Resource<FirebaseUser> {
        return try{
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            val user = result?.user
            // Store into Firestore
            val userDocument = firestore.collection("users").document(user?.uid.toString())
            val userData = hashMapOf(
                "uid" to user?.uid,
                "fullName" to fullName,
                "email" to email,
                "userType" to "CUSTOMER",
            )
            userDocument.set(userData).await()

            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(fullName).build())?.await()

            Resource.Success(result.user!!)
        } catch(e: Exception){
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun update(
        fullName: String,
        contactNum: String,
        identificationNum: String,
        newPassword: String
    ): Resource<FirebaseUser> {
        return try {
            val currentUser = currentUser
            if (currentUser != null) {
                // Update user's password if provided
                if (newPassword.isNotEmpty()) {
                    currentUser.updatePassword(newPassword).await()
                }

                // Update additional user data in Firestore
                val userDocument = firestore.collection("users").document(currentUser.uid)
                val userData = hashMapOf(
                    "fullName" to fullName,
                    "contactNum" to contactNum,
                    "identificationNum" to identificationNum
                )
                userDocument.update(userData as Map<String, Any>).await()

                Resource.Success(currentUser)
            } else {
                Resource.Error("User not authenticated")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun getFavourites(): Resource<List<DocumentReference>> {
        return try{
            val currentUser = currentUser
            if (currentUser != null) {
                val userDocument = firestore.collection("users").document(currentUser.uid)
                val documentSnapshot = userDocument.get().await()

                if (documentSnapshot.exists()) {
                    val favourites = documentSnapshot.get("favourites") as? List<DocumentReference>
                    if (favourites != null) {
                        Resource.Success(favourites)
                    } else {
                        Log.e("FAVOURITE", "REPO: No favorites found")
                        throw Exception("No favorites found")
                    }
                } else {
                    Log.e("FAVOURITE", "User not found")
                    throw Exception("User not found")
                }
            } else {
                Log.e("FAVOURITE", "User not authenticated")
                throw Exception("User not authenticated")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getUser(): Resource<User>
    {
        return try {
            val currentUser = currentUser
            if (currentUser != null) {
                val userDocument = firestore.collection("users").document(currentUser.uid).get().await()

                if (userDocument.exists()) {
                    val user = userDocument.toObject(User::class.java)
                    Resource.Success(user!!)
                } else {
                    throw Exception("User not found")
                }
            } else {
                throw Exception("User not authenticated")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }
}