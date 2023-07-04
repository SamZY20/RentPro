package com.example.rentpro.presentation.tenants.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentpro.R
import com.example.rentpro.domain.model.Property
import com.example.rentpro.navigation.NavRoutes
import com.example.rentpro.presentation.tenants.screens.PropertyDetailsScreen
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel

@Composable
fun Carousel(properties: List<Property>, navController: NavController, propertyVM: PropertyViewModel) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(properties) { property ->
            PropertyItem(property = property, navController, propertyVM = propertyVM)
        }
    }
}

@Composable
fun PropertyItem(property: Property, navController: NavController, propertyVM: PropertyViewModel) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Transparent)
            .clickable {
                propertyVM.setSelectedProperty(property)
                navController.navigate(NavRoutes.Property.route)
            }
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.rentpro_logo),
                contentDescription = "Property Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clickable { Log.i("FAVOURITE", "FAVOURITE") }
            )
        }
        Text(
            text = if (property.isAvailable) "FOR RENT" else "NOT AVAILABLE",
            style = MaterialTheme.typography.bodySmall.copy(
//                fontSize = 12.sp,
                color = Color.Gray,
                letterSpacing = 0.5.sp,
            ),

            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = property.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .width(100.dp)
            )
            Text(
                text = property.rent.toString(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(end = 8.dp, bottom = 6.dp),
            )
        }
        Text(
            text = property.description,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Gray,
        )
    }
}