package com.example.rentpro.presentation.tenants.screens.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentpro.domain.model.Property
import com.example.rentpro.domain.repository.PropertyRepository
import com.example.rentpro.utils.Resource
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val repository: PropertyRepository
):ViewModel()
{
    private val _properties: MutableStateFlow<Resource<List<Property>>> = MutableStateFlow(Resource.Loading())
    val properties: StateFlow<Resource<List<Property>>> = _properties

    private val _search: MutableStateFlow<Resource<List<Property>>> = MutableStateFlow(Resource.Loading())
    val searchedProperty: StateFlow<Resource<List<Property>>> = _search

    private val _selectedProperty = MutableStateFlow<Property?>(null)
    val selectedProperty: StateFlow<Property?> = _selectedProperty

    private val _favouriteProperty: MutableStateFlow<Resource<List<Property>>> = MutableStateFlow(Resource.Loading())
    val favouriteProperty: StateFlow<Resource<List<Property>>> = _favouriteProperty

    var resultCount: Int = 0

    fun setSelectedProperty(property: Property) {
        _selectedProperty.value = property
    }

    fun getAllProperties() = viewModelScope.launch{
        try {
            _properties.value = Resource.Loading()
            val result = repository.getAllProperties()
            _properties.value = Resource.Success(result.data!!)
        }catch (e: Exception) {
            _properties.value = Resource.Error(e.message ?: "An error occurred")
        }
    }

    fun searchProperty(keyword: String) = viewModelScope.launch {
        try{
            _search.value = Resource.Loading()
            val result = repository.searchProperty(keyword)
            resultCount = result?.data?.size!!
            _search.value = Resource.Success(result.data!!)
        } catch (e: Exception){
            _search.value = Resource.Error(e.message.toString())
        }
    }

    fun getFavouriteProperty(references: List<DocumentReference>) = viewModelScope.launch {
        try {
            _favouriteProperty.value = Resource.Loading()
            val result = repository.getFavouriteProperty(references)
            _favouriteProperty.value = Resource.Success(result.data!!)

        } catch(e: Exception){
            _favouriteProperty.value = Resource.Error(e.message.toString())
        }
    }
}