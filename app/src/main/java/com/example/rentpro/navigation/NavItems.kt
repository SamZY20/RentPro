package com.example.rentpro.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

val navItems = listOf(
    NavItem("Home", Icons.Filled.Home, NavRoutes.Home.route),
    NavItem("Explore", Icons.Filled.Search, NavRoutes.Explore.route),
    NavItem("Favorite", Icons.Filled.Favorite, NavRoutes.Favourite.route),
    NavItem("Profile", Icons.Filled.Person, NavRoutes.Profile.route)
)