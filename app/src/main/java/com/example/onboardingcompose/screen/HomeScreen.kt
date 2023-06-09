package com.example.onboardingcompose.screen

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.R
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.screen.bottomBar.NavigationGraph
import com.example.onboardingcompose.ui.theme.lite_blue


@Composable
fun HomeScreen(navControllerHome: NavHostController,navControllerMain: NavHostController,
               apiServiceModel: ApiServiceModel,sharedViewModel: SharedViewModel) {
    Scaffold(
        bottomBar = {
          BottomNavigation(navController = navControllerHome)
        }
    ) {
        NavigationGraph(navControllerMain,navControllerHome, apiServiceModel =apiServiceModel, sharedViewModel)
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Service,
        BottomNavItem.Orders,
        BottomNavItem.User,
        BottomNavItem.More
    )
    BottomNavigation(
        backgroundColor = lite_blue,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp) // Specify the desired size here
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 11.sp
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Service : BottomNavItem("Service", R.drawable.logo, "service")
    object Orders : BottomNavItem("Orders", R.drawable.order, "order")
    object User : BottomNavItem("User", R.drawable.profile, "user")
    object More : BottomNavItem("More", R.drawable.ic_more_horiz_24px, "more")
}
