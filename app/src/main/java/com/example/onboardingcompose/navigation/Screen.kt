package com.example.onboardingcompose.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome_screen")
    object Login : Screen(route = "login_screen")
    object Register : Screen(route = "Register_screen")
    object Home : Screen(route = "home_screen")
    object Smith : Screen(route = "smith_screen")
    object Map : Screen(route = "map_screen")
    object Order : Screen(route = "order_screen")
    object Profile : Screen(route = "profile_screen")
    object More : Screen(route = "more_screen")
}