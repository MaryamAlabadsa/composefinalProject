package com.example.onboardingcompose.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.screen.bottomBar.order.CompleteOrder
import com.example.onboardingcompose.screen.bottomBar.order.PendingScreen
import com.example.onboardingcompose.screen.bottomBar.order.UnderwayOrder
import com.example.onboardingcompose.ui.theme.blue
import com.example.onboardingcompose.ui.theme.lite_blue
import com.example.onboardingcompose.screen.BottomNavigation as BottomNavigation

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderScreen(
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current

    Column(Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        TopAppBar()
        Scaffold(
            topBar = {
                TopNavigationBar(
                    items = listOf(
                        BottomNavItem2(
                            name = "Pending",
                            route = "pending",
                        ),
                        BottomNavItem2(
                            name = "Underway",
                            route = "underway",
                        ),
                        BottomNavItem2(
                            name = "Completed",
                            route = "completed",
                        ),
                    ),
                    navController = navController
                ) { navController.navigate(it.route) }
            }
        ) {
            TopNavigation(navController, apiServiceModel,sharedViewModel, context)
        }
    }
}


@Composable
fun TopAppBar() {
    androidx.compose.material.TopAppBar(
        modifier = Modifier.background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    lite_blue,
                    blue,
                )
            )
        ),
        title = {
            Text(
                text = "Item Details",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp

    )
}

@ExperimentalMaterialApi
@Composable
fun TopNavigationBar(
    items: List<BottomNavItem2>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem2) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    androidx.compose.material.BottomNavigation(
        modifier = modifier.height(70.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        items.forEach { item1 ->
            val selected = item1.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item1) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,

                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        if (selected) {
                            Text(
                                text = item1.name,
                                modifier.padding(PaddingValues(top = 5.dp, bottom = 6.dp)),
                                textAlign = TextAlign.Center,
                                color = Color.Blue,
                                fontSize = 14.sp
                            )
                        } else {
                            Text(
                                text = item1.name,
                                modifier.padding(PaddingValues(top = 5.dp, bottom = 6.dp)),
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }


                }
            )
        }
    }
}
@Composable
fun TopNavigation(
    navController: NavController,
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel,
    context: Context
) {
    val selectedTab = remember { mutableStateOf("pending") }

    NavHost(navController as NavHostController, startDestination = "pending") {
        composable("pending") {
            selectedTab.value = "pending"
            PendingScreen(apiServiceModel, sharedViewModel = sharedViewModel, context)
        }
        composable("underway") {
            selectedTab.value = "underway"
            UnderwayOrder(apiServiceModel,sharedViewModel, context)
        }
        composable("completed") {
            selectedTab.value = "completed"
            CompleteOrder(apiServiceModel,sharedViewModel, context)
        }
    }
}
data class BottomNavItem2(
    val name: String,
    val route: String,
)