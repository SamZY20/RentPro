package com.example.rentpro.navigation

sealed class NavRoutes(val route: String)
{
    object Loading: NavRoutes("loading")
    object Home: NavRoutes("home")
    object Login: NavRoutes("login")
    object Registration: NavRoutes("register")
    object Explore: NavRoutes("explore")
    object Favourite: NavRoutes("favourite")
    object Profile: NavRoutes("profile")
    object Property: NavRoutes("property")
}
