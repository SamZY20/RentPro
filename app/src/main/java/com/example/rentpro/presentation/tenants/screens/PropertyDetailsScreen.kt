package com.example.rentpro.presentation.tenants.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentpro.R
import com.example.rentpro.domain.model.Property
import com.example.rentpro.presentation.widgets.MyRatingScale

@Composable
fun PropertyDetailsScreen(property: Property, navController: NavController)
{
    val context = LocalContext.current
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    
    LazyColumn(Modifier.fillMaxSize()){
        // Image Section
        item{
            Box(
                modifier = Modifier
                    .height(screenHeight * 0.5f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.img_placeholder),
                    contentDescription = "Property Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart)
                        .clickable { navController.popBackStack() }
                ) {
                    Surface(
                        color = Color.White,
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(12.dp)
                                .size(16.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopEnd)
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp)
                            .clickable { /* Handle favorite click */ }
                    )
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                Toast
                                    .makeText(context, "Link Copied!", Toast.LENGTH_LONG)
                                    .show()
                            }
                    )
                }
            }
        }

        // Property Details Section
        item{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = property.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .widthIn(max = LocalConfiguration.current.screenWidthDp.dp / 2),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                    )

                    Text(
                        text = "RM ${property.rent}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Badge(content = {
                        Text(
                            text = if (property.isAvailable) "For Rent" else "Not Available",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    },
                        containerColor = MaterialTheme.colorScheme.secondary,
                    )
                    MyRatingScale(rating = property.rating)
                    Text(
                        text = "${property.rating.toString()}.0",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Column(modifier = Modifier.fillMaxWidth())
                {
                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = property.description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Home Details",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Column() {
                        Text(text = "Location: ", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp), fontWeight = FontWeight.Bold)
                        Text(text = "Size (Sqft): ", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp), fontWeight = FontWeight.Bold)
                    }
                    Column() {
                        Text(text = property.location, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp), color = Color.Gray)
                        Text(text = property.size.toString(), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp), color = Color.Gray)
                    }

                    Column() {
                        Text(text = "Bedroom: ", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp), fontWeight = FontWeight.Bold)
                        Text(text = "Bathroom: ", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp), fontWeight = FontWeight.Bold)
                    }
                    Column() {
                        Text(text = property.numBedroom.toString(), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp), color = Color.Gray)
                        Text(text = property.numBathroom.toString(), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 8.dp), color = Color.Gray)
                    }
                }
            }
        }
    }

}