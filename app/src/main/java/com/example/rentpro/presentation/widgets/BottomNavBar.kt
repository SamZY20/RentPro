package com.example.rentpro.presentation.widgets

import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentpro.navigation.navItems

@Composable
fun BottomNavBar(navController: NavController, selectedItem: MutableState<Int>)
{

    NavigationBar(
        modifier = Modifier.height(69.dp)
    ) {
        navItems.forEachIndexed{ index, navItem->
            NavigationBarItem(
                icon = { Icon(navItem.icon, contentDescription = navItem.label) },
                label = { Text(navItem.label) },
                selected = selectedItem.value == index,
                onClick =
                {
                    selectedItem.value = index
                    navController.navigate(navItem.route)
                    {
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}