package com.example.rentpro.presentation.tenants.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.example.rentpro.presentation.auth.viewModels.AuthViewModel
import com.example.rentpro.presentation.tenants.components.FeaturedProperty
import com.example.rentpro.presentation.tenants.components.Highlights
import com.example.rentpro.presentation.tenants.components.OurRecommendation
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel
import com.example.rentpro.presentation.widgets.BottomNavBar
import com.example.rentpro.presentation.widgets.MySearchBar
import com.example.rentpro.presentation.widgets.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: AuthViewModel?, propertyVM: PropertyViewModel?, navController: NavController)
{
    val localFocusManager = LocalFocusManager.current
    var _searchKeyword by remember{ mutableStateOf("") }
    var selectedItem = remember { mutableStateOf(0) }

    Scaffold(
        topBar = { TopBar(viewModel, navController) },
        content = {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            localFocusManager.clearFocus()
                        })
                    },) {
//                item{
//                    TopBar(viewModel?.currentUser?.displayName.toString(), navController)
//                }

                // Search bar
                item{
                    MySearchBar(
                        searchKeyword = _searchKeyword,
                        onSearchKeywordChange = { keyword -> _searchKeyword = keyword }
                    )
                }

                // Featured Property
                item{
                    FeaturedProperty(navController = navController, propertyVM = propertyVM!!)
                }

                // Recommendation
                item{
                    OurRecommendation(navController = navController, propertyVM = propertyVM!!)
                }

                // Information
                item{
                    Highlights();
                }
            }
        },
        bottomBar = { BottomNavBar(navController, selectedItem) },
    )
}
