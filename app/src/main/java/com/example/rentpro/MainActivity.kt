package com.example.rentpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.rentpro.domain.model.Property
import com.example.rentpro.navigation.AppNavHost
import com.example.rentpro.navigation.NavRoutes
import com.example.rentpro.presentation.auth.viewModels.AuthViewModel
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel
import com.example.rentpro.ui.theme.RentProTheme
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<AuthViewModel>()
    private val propertyVM by viewModels<PropertyViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentProTheme {
//                val isLoading by viewModel.isLoading.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(viewModel, propertyVM)
                }
            }
        }
    }
}
