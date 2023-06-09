package com.example.onboardingcompose.screen

import android.preference.PreferenceManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.example.onboardingcompose.ui.theme.blue
import com.example.onboardingcompose.ui.theme.gray
import com.example.onboardingcompose.ui.theme.gray_bc
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavHostController,
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel,
//    data: Int
) {
    var userData=sharedViewModel.getUserProfile()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)

            .verticalGradientBackground(),
        horizontalAlignment = Alignment.End,
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 4.dp,
            shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 85.dp)
                , horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = userData.name.toString(),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 10.dp)
                )
            }

            Text(
                text = userData.email.toString(),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 130.dp, bottom = 20.dp),
                fontSize = 16.sp
            )

        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            elevation = 4.dp,
        ) {
            Column() {
                ProfileItem(name = "Language", "English")
                ProfileItem(name = "My Rates", "")
                ProfileItem(name = "Contact us", "")
                ProfileItem(name = "Share App", "")
            }

        }

        Button(
            onClick = {

            }, modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 7.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            )
        ) {
            Image(
                painterResource(id = R.drawable.ic_join),
                contentDescription = "SIGN OUT", modifier = Modifier.size(30.dp)
            )

            Text(
                text = "SIGN OUT",
                color = blue,
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 18.sp
            )
        }
    }

    RoundCornerImageView()


}
@Composable
fun ProfileItem(name: String, des: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp) // Adjust padding as needed
    ) {
        Text(
            text = name,
            color = Color.Black,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        if (des.isNotEmpty()) {
            Text(
                text = des,
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun RoundCornerImageView() {
    Image(
        painter = painterResource(R.drawable.img),
        contentDescription = "Round corner image",
        contentScale = ContentScale.Crop,

        modifier = Modifier
            .height(170.dp)
            .fillMaxWidth()
            .padding(top = 50.dp, start = 120.dp, end = 120.dp)
            .clip(RoundedCornerShape(10))
            .border(5.dp, Color.White, RoundedCornerShape(10))
    )
}
