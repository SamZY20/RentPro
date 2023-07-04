package com.example.rentpro.presentation.tenants.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentpro.domain.model.Property
import com.example.rentpro.presentation.tenants.components.Carousel
import com.example.rentpro.presentation.tenants.components.PropertyList
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel
import com.example.rentpro.presentation.widgets.BottomNavBar
import com.example.rentpro.presentation.widgets.MySearchBar
import com.example.rentpro.utils.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(navController: NavController, propertyVM: PropertyViewModel)
{
    val localFocusManager = LocalFocusManager.current
    var _searchKeyword by remember{ mutableStateOf("") }
    var selectedItem = remember { mutableStateOf(1) }

    val searchedProperty by propertyVM.searchedProperty.collectAsState()

    LaunchedEffect(_searchKeyword) {
        propertyVM.searchProperty(_searchKeyword)
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

                // Search bar
                MySearchBar(
                    searchKeyword = _searchKeyword,
                    onSearchKeywordChange = { keyword -> _searchKeyword = keyword }
                )

                // Title
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "Results",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 16.dp),
                    )
                    Text(
                        text = "${propertyVM.resultCount} Results Found",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Gray,
                        fontSize = 10.sp,
                    )
                }

                // Property Listing
                when(searchedProperty){
                    is Resource.Loading -> {
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            Text(text = "Loading...",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            CircularProgressIndicator()
                        }
                    }
                    is Resource.Success -> {
                        val properties = (searchedProperty as Resource.Success<List<Property>>).data
                        PropertyList(propertyList = properties!!, propertyVM = propertyVM, navController = navController)
                    }
                    is Resource.Error -> {
                        val error = (searchedProperty as Resource.Error<List<Property>>).message
                        Log.e("ERROR", error!!)
                    }
                }
            }
        },
        bottomBar = { BottomNavBar(navController, selectedItem) },
    )
}

