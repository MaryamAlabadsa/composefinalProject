package com.example.onboardingcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.R
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.navigation.Screen

@Composable
fun MoreScreen(
    navControllerHome: NavHostController,
    navControllerMain: NavHostController,
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel,
//    data: Int
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.bc2),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(101.dp),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,

                ) {
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Notification Icon",
                        tint = Color.White,
                        modifier = Modifier.clickable { navControllerHome.popBackStack() }

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(
                            text = "More",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
        MoreItem(name = "Change Password")
        MoreItem(name = "FAQ's")
        MoreItem(name = "About App")
        MoreItem(name = "Terms & Conditions")
        MoreItem(name = "Privacy Policy")
        MoreItem(name = "Rate App")
        MoreItem(
            name = "Log Out",
            clickable = true,
            onClick = {
                sharedViewModel.clearUserData()
                navControllerMain.apply {
                    popBackStack() // Close and finish the current destination
                    navigate(Screen.Login.route)
                }
                navControllerHome.apply {
                    popBackStack() // Close and finish the current destination
                }
            }
        )

    }
}

@Composable
fun MoreItem(name: String, clickable: Boolean = false, onClick: () -> Unit = {}) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = name,
                color = colorResource(id = R.color.black),
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
            )

            if (clickable) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = " ",
                    tint = Color.Gray,
                    modifier = Modifier.clickable { onClick() }
                )
            } else {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = " ",
                    tint = Color.Gray
                )
            }
        }
    }
}

