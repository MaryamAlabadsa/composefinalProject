package com.example.onboardingcompose.screen

import android.content.Context
import android.net.Uri
import android.provider.Settings.Secure.putInt
import android.provider.Settings.Secure.putString
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberImagePainter
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.R
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.navigation.Screen
import com.example.onboardingcompose.screen.bottomBar.NavigationGraph
import com.example.onboardingcompose.ui.theme.gray
import com.google.gson.Gson
import java.io.File
import java.io.FileOutputStream


@Composable
fun SmithScreen(
    navController: NavHostController,
    apiServiceModel: ApiServiceModel,
    sharedViewModel: SharedViewModel,
    data: Int
) {
    val context = LocalContext.current
    var details by remember { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        selectedImage = it
    }

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
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Notification Icon",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Smith",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }


        Box(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth()
            .height(60.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color.Blue,
                    style = Stroke(
                        width = 2f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                )
            }
            .border(
                width = 1.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { galleryLauncher.launch("image/*") }) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.White)
            ) {
                if (selectedImage != null) {
                    Image(
                        painter = rememberImagePainter(selectedImage),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(50.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.second),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "Pick Image",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                }


            }

        }
        Text(
            text = "Image must be no more than 2 MB Max 5 Image",
//            textAlign = TextAlign.Center,
            fontSize = 11.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(top = 340.dp)
                .padding(start = 16.dp, end = 16.dp, top = 340.dp),
//            ,
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(),
                visible = !details.equals("") && selectedImage != null
            ) {
                Button(
                    onClick = {

                        val gson = Gson()
                        val dataJson = gson.toJson(data)
                        navController.navigate(
                            Screen.Map.route + "/$dataJson"+"/$details"
//                            bundleOf("des" to details)
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}

private fun convertUriToFile(context: Context, uri: Uri): File {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri)
    val outputFile = File(context.cacheDir, "temp_image")
    val outputStream = FileOutputStream(outputFile)

    inputStream?.copyTo(outputStream)
    inputStream?.close()
    outputStream.close()

    return outputFile
}
//@Composable
//fun DashedBorderModifier(
//    strokeWidth: Dp = 1.dp,
//    dashWidth: Dp = 5.dp,
//    dashGap: Dp = 5.dp,
//    color: Color = Color.Black,
//    shape: Shape = RectangleShape,
//    content: @Composable () -> Unit
//) {
//    Box(modifier = Modifier
//        .border(
//            BorderStroke(strokeWidth, color),
//            shape = shape
//        )
//        .padding(strokeWidth / 2f)
//        .background(
//            brush = Brush.verticalGradient(listOf(color, color)),
//            shape = shape
//        )
//        .padding(-strokeWidth / 2f)
//    ) {
//        content()
//    }
//}

