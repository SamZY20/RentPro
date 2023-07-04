package com.example.rentpro.presentation.tenants.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentpro.domain.model.Property
import com.example.rentpro.R
import com.example.rentpro.presentation.tenants.screens.viewModels.PropertyViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun PropertyList(propertyList: List<Property>, propertyVM: PropertyViewModel, navController: NavController)
{
    /*val results: MutableList<Property> = remember {
        mutableStateListOf(
            Property(
                id = "1",
                title = "Spacious Apartment - A modern and spacious apartment in a prime location.",
                description = "A modern and spacious apartment in a prime location.",
                rent = 1500.0,
                location = "City Center",
                numBathroom = 2,
                numBedroom = 3,
                size = 120.0,
                rating = 4,
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "2",
                title = "Cozy House",
                description = "A cozy house with a beautiful garden.",
                rent = 2000.0,
                location = "Suburb",
                numBathroom = 1,
                numBedroom = 2,
                size = 80.0,
                rating = 3,
                isAvailable = false,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "3",
                title = "Luxury Villa",
                description = "A luxurious villa with stunning ocean views.",
                rent = 5000.0,
                location = "Beachfront",
                numBathroom = 4,
                numBedroom = 5,
                size = 350.0,
                rating = 5,
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "1",
                title = "Spacious Apartment",
                description = "A modern and spacious apartment in a prime location.",
                rent = 1500.0,
                location = "City Center",
                numBathroom = 2,
                numBedroom = 3,
                size = 120.0,
                rating = 4,
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "2",
                title = "Cozy House",
                description = "A cozy house with a beautiful garden.",
                rent = 2000.0,
                location = "Suburb",
                numBathroom = 1,
                numBedroom = 2,
                size = 80.0,
                rating = 3,
                isAvailable = false,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "3",
                title = "Luxury Villa",
                description = "A luxurious villa with stunning ocean views.",
                rent = 5000.0,
                location = "Beachfront",
                numBathroom = 4,
                numBedroom = 5,
                size = 350.0,
                rating = 5,
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "1",
                title = "Spacious Apartment",
                description = "A modern and spacious apartment in a prime location.",
                rent = 1500.0,
                location = "City Center",
                numBathroom = 2,
                numBedroom = 3,
                size = 120.0,
                rating = 4,
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "2",
                title = "Cozy House",
                description = "A cozy house with a beautiful garden.",
                rent = 2000.0,
                location = "Suburb",
                numBathroom = 1,
                numBedroom = 2,
                size = 80.0,
                rating = 3,
                isAvailable = false,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "3",
                title = "Luxury Villa",
                description = "A luxurious villa with stunning ocean views.",
                rent = 5000.0,
                location = "Beachfront",
                numBathroom = 4,
                numBedroom = 5,
                size = 350.0,
                rating = 5,
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "1",
                title = "Spacious Apartment",
                description = "A modern and spacious apartment in a prime location.",
                rent = 1500.0,
                location = "City Center",
                numBathroom = 2,
                numBedroom = 3,
                size = 120.0,
                rating = 4,
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "2",
                title = "Cozy House",
                description = "A cozy house with a beautiful garden.",
                rent = 2000.0,
                location = "Suburb",
                numBathroom = 1,
                numBedroom = 2,
                size = 80.0,
                rating = 3,
                isAvailable = false,
                landlord = "1",
                created_at = Timestamp.now()
            ),
            Property(
                id = "3",
                title = "Luxury Villa",
                description = "A luxurious villa with stunning ocean views.",
                rent = 5000.0,
                location = "Beachfront",
                numBathroom = 4,
                numBedroom = 5,
                size = 350.0,
                rating = 5,
                isAvailable = true,
                landlord = "1",
                created_at = Timestamp.now()
            )
        )
    }*/

    if(propertyList.isEmpty()){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.no_result), contentDescription = "No Results")
            Text(
                text = "No Results Found",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

    } else {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 56.dp)) {
            items(propertyList) { property ->
                PropertyCard(property, propertyVM = propertyVM, navController = navController)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}