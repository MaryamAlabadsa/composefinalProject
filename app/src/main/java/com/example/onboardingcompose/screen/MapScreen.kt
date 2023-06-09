package com.example.onboardingcompose.screen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.R
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.model.request.OrderRequest
import com.example.onboardingcompose.navigation.Screen
import com.google.android.gms.location.LocationServices

@Composable
fun MapScreen(
    navControllerHome: NavHostController,
    navControllerMain: NavHostController,
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel,
    data: Int,
    des: String
) {
    val context = LocalContext.current
    var details by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var isLoading by remember { mutableStateOf(false) }


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
        }
        OutlinedTextField(
            value = details,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(start = 16.dp, end = 16.dp, top = 40.dp),
            onValueChange = { details = it },
            label = { Text(text = "Details") },
            placeholder = { Text(text = "More Details About Problem ...") }
        )
        OutlinedTextField(
            value = phone,
            modifier = Modifier.fillMaxWidth().height(120.dp)
                .padding(start = 16.dp, end = 16.dp, top = 40.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "phone"
                )
            },
            onValueChange = { phone = it },
            label = { Text(text = "Phone") },
            placeholder = { Text(text = "Enter your Phone") },
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 340.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(),
                visible = !phone.equals("") && details.isNotEmpty()
            ) {
                Button(
                    onClick = {
                        isLoading = true
                        val orderRequest = OrderRequest(data, des, details, phone, 8787, 9898)
                        apiServiceModel.createOrder(
                            sharedViewModel,
                            context,
                            orderRequest,
                            navControllerHome,navControllerMain
                        )
                        val orderData = apiServiceModel.liveOrderData
                        if (orderData == null) {
                            isLoading = false
                            Toast.makeText(context, "Error occurred", Toast.LENGTH_LONG).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    )
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = Color.White)
                    } else {
                        Text(text = "Save")
                    }
                }
            }

        }
    }
}

fun retrieveLocation(context: Context, locationCallback: (Double, Double) -> Unit) {
    // Implement your location retrieval logic here
    // Once you have obtained the latitude and longitude, call the locationCallback
    // For example:
    val latitude = 0.0
    val longitude = 0.0
    locationCallback(latitude, longitude)
}

