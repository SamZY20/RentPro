package com.example.rentpro.presentation.tenants.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentpro.domain.model.Property
import com.example.rentpro.presentation.auth.viewModels.AuthViewModel
import com.example.rentpro.presentation.tenants.components.PropertyList
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel
import com.example.rentpro.presentation.widgets.BottomNavBar
import com.example.rentpro.utils.Resource
import com.google.firebase.firestore.DocumentReference

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(authViewModel: AuthViewModel, navController: NavController, propertyVM: PropertyViewModel)
{
    val localFocusManager = LocalFocusManager.current
    var selectedItem = remember { mutableStateOf(2) }

    val getFavourites by authViewModel.favourites.collectAsState()
    val getFavouriteProperty by propertyVM.favouriteProperty.collectAsState()

    LaunchedEffect(Unit){
        authViewModel.getFavourites()
    }

    when(getFavourites){
        is Resource.Loading->{}
        is Resource.Success->{
            propertyVM.getFavouriteProperty(getFavourites.data!!)
        }
        is Resource.Error->{
            propertyVM.getFavouriteProperty(emptyList())
        }
    }


    Scaffold(
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            localFocusManager.clearFocus()
                        })
                    },) {

                // Title
                Text(
                    text = "Favourites",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                // Property Listing
                when(getFavouriteProperty){
                    is Resource.Loading->{
                    }
                    is Resource.Success->{
                        PropertyList(getFavouriteProperty.data!!, propertyVM = propertyVM, navController = navController)
                    }
                    is Resource.Error->{
                        Log.e("FAVOURITE", "PL: ${getFavouriteProperty.message.toString()}")
                    }
                }
            }
        },
        bottomBar = { BottomNavBar(navController, selectedItem) },
    )
}