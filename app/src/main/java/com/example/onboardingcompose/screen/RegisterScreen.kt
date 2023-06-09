package com.example.onboardingcompose.screen


import android.widget.Toast
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.R
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.model.request.RegisterRequest
import com.example.onboardingcompose.navigation.Screen

@Composable
fun RegisterScreen(
    navController: NavHostController,
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel,
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

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
        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 130.dp)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                ClickableRow()
                // Email field
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("full name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
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
//                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    // Remember me checkbox
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { isChecked = it },
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text("I Read and Accept Home Service Terms & Conditions")
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
                        if (email.equals("") || password.equals("") ||
                            fullName.equals("") || phone.equals("") ||
                            !isChecked
                        ) {
                            Toast.makeText(context, "checked your data", Toast.LENGTH_LONG).show();
                        } else {
                            var data =
                                RegisterRequest(fullName, email, password, Integer.parseInt(phone));
                            apiServiceModel.registerUser(
                                data,
                                sharedViewModel,
                                navController,
                                context
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalGradientBackground(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),

                    elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
                ) {
                    Text("Sign in")
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                        .clickable {
                            navController.popBackStack()
                            navController.navigate(Screen.Login.route)
                        }
                ) {
                    Text("New Member? ", style = TextStyle.Default)
                    Text(
                        text = "Sign in",
                        style = TextStyle.Default.copy(textDecoration = TextDecoration.Underline)
                    )
                }

            }
        }
    }
}

@Composable
fun ClickableRow() {
    var selectedItem by remember { mutableStateOf(SelectedItem.Customer) }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable { selectedItem = SelectedItem.Customer }
//                .padding(horizontal = 30.dp)
                .weight(1f)
        ) {
            Text(
                text = "Customer",
                color = if (selectedItem == SelectedItem.Customer) Color(0xFF346EDF) else Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
//                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )
            Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .height(3.dp),
                color = if (selectedItem == SelectedItem.Customer) Color(0xFF346EDF) else Color.Transparent
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable { selectedItem = SelectedItem.ServiceProvider }
//                .padding(horizontal = 30.dp)
                .weight(1f)
        ) {
            Text(
                text = "Service Provider",
                color = if (selectedItem == SelectedItem.ServiceProvider) Color(0xFF346EDF) else Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
//                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )
            Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .height(3.dp),
                color = if (selectedItem == SelectedItem.ServiceProvider) Color(0xFF346EDF) else Color.Transparent
            )
        }
    }
}

enum class SelectedItem {
    Customer,
    ServiceProvider
}


@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview() {
//    RegisterScreen()
    ClickableRow()
}