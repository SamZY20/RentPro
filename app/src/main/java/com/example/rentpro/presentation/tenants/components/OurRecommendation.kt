package com.example.rentpro.presentation.tenants.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentpro.domain.model.Property
import com.example.rentpro.navigation.NavRoutes
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel
import com.google.firebase.Timestamp

@Composable
fun OurRecommendation(navController: NavController, propertyVM: PropertyViewModel)
{
    val recommendedProperties = remember {
        mutableStateListOf(
            Property(
                id = "1",
                title = "Luxurious Condo in the City Center",
                description = "Stunning condo located in the heart of the city, offering modern amenities and breathtaking views. Spacious living area, gourmet kitchen, and luxurious master suite.",
                rent = 2500.0,
                location = "City Center",
                numBathroom = 2,
                numBedroom = 3,
                size = 1500.0,
                rating = 4,
                imagePath = "property1_image.jpg",
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "2",
                title = "Cozy Apartment near the Beach",
                description = "Charming apartment just a short walk from the beach. Features a cozy living space, fully equipped kitchen, and a private balcony with ocean views.",
                rent = 1800.0,
                location = "Beachside",
                numBathroom = 1,
                numBedroom = 2,
                size = 900.0,
                rating = 4,
                imagePath = "property2_image.jpg",
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "3",
                title = "Spacious Family Home with Garden",
                description = "Beautiful family home with a large garden, perfect for outdoor activities. Open floor plan, modern kitchen, and comfortable bedrooms. Located in a peaceful neighborhood.",
                rent = 3000.0,
                location = "Suburb",
                numBathroom = 3,
                numBedroom = 4,
                size = 2000.0,
                rating = 4,
                imagePath = "property3_image.jpg",
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Our Recommendation",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "See all",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(end = 16.dp)
                .clickable{ navController.navigate(NavRoutes.Explore.route) }
        )
    }

    Carousel(properties = recommendedProperties, navController = navController, propertyVM = propertyVM)
}