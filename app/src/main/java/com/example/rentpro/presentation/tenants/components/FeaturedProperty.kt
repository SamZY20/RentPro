package com.example.rentpro.presentation.tenants.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentpro.domain.model.Property
import com.example.rentpro.navigation.NavRoutes
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel
import com.example.rentpro.utils.Resource

@Composable
fun FeaturedProperty(navController: NavController, propertyVM: PropertyViewModel)
{
    var featuredProperties by remember { mutableStateOf<Resource<List<Property>>>(Resource.Loading()) }

    LaunchedEffect(Unit) {
        propertyVM.getAllProperties()
        propertyVM.properties.collect { state ->
            featuredProperties = state
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Featured Properties",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "See all",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(end = 16.dp).clickable{ navController.navigate(NavRoutes.Explore.route) }
        )
    }

    when(featuredProperties){
        is Resource.Loading ->
        {
            // LOADING
        }
        is Resource.Success -> {
            val properties = (featuredProperties as Resource.Success<List<Property>>).data
            Carousel(properties = properties!!, navController, propertyVM = propertyVM)
        }
        is Resource.Error -> {
            val error = (featuredProperties as Resource.Error<List<Property>>).message
            Log.e("ERROR", error!!)
        }
    }
}