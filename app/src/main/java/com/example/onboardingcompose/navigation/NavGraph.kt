package com.example.onboardingcompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.screen.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.gson.Gson

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navControllerMain: NavHostController,
    navControllerHome: NavHostController,
    startDestination: String,
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel,
    ) {
    NavHost(
        navController = navControllerMain,
        startDestination = startDestination
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navControllerMain)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navControllerMain, apiServiceModel,sharedViewModel)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navControllerMain, apiServiceModel,sharedViewModel)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navControllerHome, navControllerMain, apiServiceModel, sharedViewModel)
        }
        composable(
            "${Screen.Smith.route}/{data}", // Include the data argument in the route
            arguments = listOf(navArgument("data") { type = NavType.IntType }) // Define the type of the argument
        ) { navBackStackEntry ->
            val data = navBackStackEntry.arguments?.getInt("data") // Retrieve the data from arguments
            if (data != null) {
                // Use the data in your Smith screen
                SmithScreen(navControllerMain,apiServiceModel,sharedViewModel,data)
            }
        }
        composable(
            "${Screen.Map.route}/{data}/{details}",
            arguments = listOf(
                navArgument("data") { type = NavType.IntType },
                navArgument("details") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val data = navBackStackEntry.arguments?.getInt("data")
            val details = navBackStackEntry.arguments?.getString("details")
            if (data != null && details != null) {
//                val gson = Gson()
//                val dataObject = gson.fromJson(data.toString(), YourDataType::class.java)
                MapScreen(navControllerMain=navControllerMain, navControllerHome = navControllerHome,
                    apiServiceModel = apiServiceModel, sharedViewModel =  sharedViewModel, data =  data, des = details)
            }
        }


//        composable(route = Screen.Map.route) { backStackEntry ->
//            val data = backStackEntry.arguments?.getInt("data") ?: 0
//            val des = backStackEntry.arguments?.getString("des") ?: ""
//        }


    }
}