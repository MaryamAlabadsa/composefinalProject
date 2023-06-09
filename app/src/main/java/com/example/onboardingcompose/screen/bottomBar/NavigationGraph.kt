package com.example.onboardingcompose.screen.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.screen.*

@Composable
fun NavigationGraph(
    navControllerMain: NavHostController,
    navControllerHome: NavHostController,
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel
) {
    NavHost(navControllerHome, startDestination = BottomNavItem.Service.screen_route) {
        composable(BottomNavItem.Service.screen_route) {
            ServiceScreen(apiServiceModel, sharedViewModel, navControllerMain)
        }
        composable(BottomNavItem.Orders.screen_route) {
            OrderScreen(
                apiServiceModel = apiServiceModel,
                sharedViewModel = sharedViewModel,
                navController = navControllerMain
            )
        }
        composable(BottomNavItem.User.screen_route) {
            ProfileScreen(
                apiServiceModel = apiServiceModel,
                sharedViewModel = sharedViewModel,
                navController = navControllerMain
            )
        }
        composable(BottomNavItem.More.screen_route) {
            MoreScreen(
                apiServiceModel = apiServiceModel,
                sharedViewModel = sharedViewModel,
                navControllerHome = navControllerHome,
                navControllerMain = navControllerMain
            )
        }
    }
}