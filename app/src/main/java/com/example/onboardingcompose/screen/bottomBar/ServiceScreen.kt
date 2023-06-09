package com.example.onboardingcompose.screen.bottomBar

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.R
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.navigation.Screen
import com.example.onboardingcompose.ui.theme.bink
import com.example.onboardingcompose.ui.theme.blue
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServiceScreen(
    apiServiceModel: ApiServiceModel, sharedViewModel: SharedViewModel, navController: NavController
) {
    val isLoading = remember { mutableStateOf(true) }
    val context = LocalContext.current
    apiServiceModel.allWorkData(sharedViewModel, context)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.bc1), // Replace with your background image resource
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo2), // Replace with your logo resource
                            contentDescription = "Logo",
                            modifier = Modifier
                                .height(34.dp)
                                .width(56.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notification Icon",
                        tint = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Select Service",
            style = TextStyle(/* Define your text style */)
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
        } else {
            LazyVerticalGrid(
                cells = GridCells.Fixed(4),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
                items(apiServiceModel.liveAllWorkData) { allWorkData ->
                    allWorkData.icon?.let {
                        allWorkData.name?.let { it1 ->
                            CardItem(
                                icon = it,
                                text = it1,
                                onClick = {

                                    navController.navigate(
                                        "${Screen.Smith.route}/${allWorkData.id}" // Pass the data as part of the route
                                    )
                                }
                            )
                        }
                    }
                }
            }

        }
    }

    // Simulating data loading. Replace this with your actual data fetching code.
    LaunchedEffect(Unit) {
        delay(2000) // Simulating a delay of 2 seconds
        isLoading.value = false
    }
}


@Composable
fun CardItem(
    icon: String,
    text: String,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .width(130.dp)
            .padding(bottom = 6.dp, end = 8.dp, start = 8.dp, top = 6.dp)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(0.5.dp, Color.Red)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(icon)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.logo2),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = text, color = Color.Black)
        }
    }
}

