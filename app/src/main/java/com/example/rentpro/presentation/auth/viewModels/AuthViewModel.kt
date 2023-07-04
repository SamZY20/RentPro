package com.example.rentpro.presentation.auth.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentpro.domain.model.Property
import com.example.rentpro.domain.model.User
import com.example.rentpro.domain.repository.AuthRepository
import com.example.rentpro.utils.Resource
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.w3c.dom.Document
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel()
{
    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _registerFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val registerFlow: StateFlow<Resource<FirebaseUser>?> = _registerFlow

    private val _updateFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val updateFlow: StateFlow<Resource<FirebaseUser>?> = _updateFlow

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _favourites: MutableStateFlow<Resource<List<DocumentReference>>> = MutableStateFlow(Resource.Loading())
    val favourites: StateFlow<Resource<List<DocumentReference>>> = _favourites

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    // Get Logged-in User
    val currentUser: FirebaseUser?
        get() = repository.currentUser


    init{
        if(repository.currentUser != null){
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

     fun login(email: String, password: String) = viewModelScope.launch{
        _loginFlow.value = Resource.Loading()
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    fun register(fullName: String, email: String, password: String, confirmPassword: String) = viewModelScope.launch{
        _registerFlow.value = Resource.Loading()
        if(password != confirmPassword)
        {
            _registerFlow.value = Resource.Error("Passwords do not match")
        } else {
            val result = repository.register(fullName, email, password)
            _registerFlow.value = result
        }
    }

    fun update(fullName: String, contactNum: String, identificationNum: String, newPassword: String) = viewModelScope.launch {
        _updateFlow.value = Resource.Loading()
        val result = repository.update(fullName, contactNum, identificationNum, newPassword)
        _updateFlow.value = result
    }

    fun logout()
    {
        repository.logout()
        _loginFlow.value = null
        _registerFlow.value = null
    }

    fun getFavourites() = viewModelScope.launch{
        try {
            _favourites.value = Resource.Loading()
            val result = repository.getFavourites()
            _favourites.value = Resource.Success(result.data!!)

        } catch(e: Exception){
            _favourites.value = Resource.Error(e.message.toString())
        }
    }

    fun getUser() = viewModelScope.launch {
        try {
            val result = repository.getUser()
            if (result is Resource.Success) {
                val user = result.data
                _user.value = user
            } else if (result is Resource.Error) {
                Log.e(TAG, "Failed to fetch user: ${result.message}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception in getUser: ${e.message}")
        }
    }
}