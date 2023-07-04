package com.example.rentpro.presentation.tenants.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyCard(property: Property, propertyVM: PropertyViewModel, navController: NavController) {
    Surface(modifier = Modifier.background(color = Color.Transparent).clickable {
        propertyVM.setSelectedProperty(property)
        navController.navigate(NavRoutes.Property.route)
    }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(15.dp)
        ) {
            // Image
            Image(painter = painterResource(
                id = R.drawable.img_placeholder),
                contentDescription = property.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 7.dp))

            // Title and Rent
            Column(modifier = Modifier
                .weight(1f)
                .fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Text(
                    text = property.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "RM ${property.rent}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp),
                )

                // Icon and Text
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(id = R.drawable.bed), contentDescription = "Bed", Modifier.size(26.dp))
                        Text(text = "${property.numBedroom}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                    }
                    Text(text="/", color = Color.Gray)
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(id = R.drawable.bathroom), contentDescription = "Bathroom", Modifier.size(23.dp))
                        Text(text = "${property.numBathroom}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                    }
                    Text(text="/", color = Color.Gray)
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(id = R.drawable.sqft), contentDescription = "Sqft", Modifier.size(23.dp))
                        Text(text = "${property.size}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}