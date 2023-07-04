package com.example.rentpro.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentpro.R
import com.example.rentpro.navigation.NavRoutes
import com.example.rentpro.presentation.auth.viewModels.AuthViewModel
import java.util.*

@Composable
fun TopBar(viewModel: AuthViewModel?, navController: NavController)
{
    var expanded by remember { mutableStateOf(false) }
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val greeting = when (currentHour) {
        in 0..11 -> "Good Morning!"
        in 12..16 -> "Good Afternoon!"
        else -> "Good Evening!"
    }

    TopAppBar(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth(),
        contentColor = LocalContentColor.current,
        title = {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(
                        text = greeting,
                        color = Color(0xFF8A8A8A),
                        style = LocalTextStyle.current.copy(fontSize = 12.sp,fontWeight = FontWeight.Light),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = viewModel?.currentUser?.displayName.toString(),
                        color = Color.Black,
                        style = LocalTextStyle.current.copy(fontSize = 16.sp,fontWeight = FontWeight.Bold),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier.clickable(
                        onClick = { expanded = !expanded },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),

                ) {
                    Image(
                        painter = painterResource(R.drawable.user),
                        contentDescription = "User Image",
                        modifier = Modifier
                            .size(45.dp)
                            .padding(end = 8.dp)
                    )
                }
            }
            Box(modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.TopEnd)) {
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Profile") },
                        onClick = { navController.navigate(NavRoutes.Profile.route) })

                    DropdownMenuItem(text = { Text(text = "Logout") }, onClick = {
                        viewModel?.logout()
                        navController.navigate(NavRoutes.Login.route) {
                            popUpTo(NavRoutes.Home.route) { inclusive = true }
                        }
                    })
                }
            }
        },
    )
}