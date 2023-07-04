package com.example.rentpro.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rentpro.domain.model.Property
import com.example.rentpro.presentation.auth.screens.LoginScreen
import com.example.rentpro.presentation.auth.screens.RegistrationScreen
import com.example.rentpro.presentation.auth.viewModels.AuthViewModel
import com.example.rentpro.presentation.screens.LoadingScreen
import com.example.rentpro.presentation.tenants.screens.*
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel
import kotlinx.coroutines.delay


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    viewModel: AuthViewModel,
    propertyVM: PropertyViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoutes.Loading.route
)
{
    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
        composable(NavRoutes.Loading.route) {
            LoadingScreen()
        }

        composable(NavRoutes.Login.route){
            LoginScreen(viewModel, navController = navController)
        }
        composable(NavRoutes.Registration.route)
        {
            RegistrationScreen(viewModel = viewModel, navController = navController)
        }
        composable(NavRoutes.Home.route)
        {
            HomeScreen(viewModel = viewModel, propertyVM = propertyVM, navController = navController)
        }
        composable(NavRoutes.Explore.route)
        {
            ExploreScreen(navController = navController, propertyVM = propertyVM)
        }
        composable(NavRoutes.Favourite.route)
        {
            FavouriteScreen(authViewModel = viewModel, navController = navController, propertyVM = propertyVM)
        }
        composable(NavRoutes.Profile.route)
        {
            ProfileScreen(viewModel = viewModel, navController = navController)
        }
        composable(NavRoutes.Property.route)
        {
            val property = propertyVM.selectedProperty.collectAsState().value
            PropertyDetailsScreen(property = property!!, navController = navController)
        }
    }

    LaunchedEffect(Unit) {
        delay(3000)
        val initialRoute =
            if (viewModel.currentUser != null) NavRoutes.Home.route
            else NavRoutes.Login.route
        navController.navigate(initialRoute) {
            popUpTo(navController.graph.startDestinationId) {inclusive = true}
            launchSingleTop = true
        }
    }
}