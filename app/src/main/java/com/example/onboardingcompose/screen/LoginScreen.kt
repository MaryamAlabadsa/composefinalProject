package com.example.onboardingcompose.screen


import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.R
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.model.request.LoginRequest
import com.example.onboardingcompose.navigation.Screen
import com.example.onboardingcompose.ui.theme.blue
import com.example.onboardingcompose.ui.theme.lite_blue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavHostController,
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (isLoading) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(288.dp)
                .verticalGradientBackground()
//                .padding(16.dp)
            , verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo2),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp)
            )
        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 220.dp)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                ClickableRow()
                // Email field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                // Password field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)

                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    // Remember me checkbox
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { isChecked = it },
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text("Remember me")
                    }

                    // Forgot password
                    Text(
                        "Forgot Password?",
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .clickable { /* Handle click */ })
                }

                Button(
                    onClick = {
                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(context, "Please check your data", Toast.LENGTH_LONG).show()
                        } else {
                            var loginRequest = LoginRequest(email, password)
                            isLoading = true
                            CoroutineScope(Dispatchers.Main).launch {
                                // Simulate an asynchronous login process
                                delay(2000) // Replace this with your actual login logic
                                val userData = apiServiceModel.getUser(
                                    loginRequest = loginRequest,
                                    context = context,
                                    sharedViewModel=sharedViewModel
                                )
                                if (isChecked) {
                                    // Handle successful login
                                    if (userData != null) {
                                        sharedViewModel.setUserProfile(userData)
                                        userData.token?.let { sharedViewModel.setToken(it) }
                                    }
                                    // Navigate to the next screen after successful login
                                    navController.navigate(Screen.Home.route)
                                }

                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .verticalGradientBackground(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp),
                            color = MaterialTheme.colors.primary
                        )
                    }
                    Text("Login")
                }





                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                        .clickable {
                            navController.popBackStack()
                            navController.navigate(Screen.Register.route)
                        }
                ) {
                    Text("New Member? ", style = TextStyle.Default)
                    Text(
                        text = "Sign up",
                        style = TextStyle.Default.copy(textDecoration = TextDecoration.Underline)
                    )
                }

            }
        }
    }
}

fun Modifier.verticalGradientBackground(): Modifier = this.then(
    Modifier.background(
        brush = Brush.horizontalGradient(
            colors = listOf(
               blue,
                lite_blue
            )
        )
    )
)


//@Composable
//@Preview(showBackground = true)
//fun LoginScreenPreview() {
//    LoginScreen()
//}