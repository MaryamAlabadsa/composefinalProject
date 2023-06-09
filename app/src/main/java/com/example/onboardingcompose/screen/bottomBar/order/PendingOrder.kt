package com.example.onboardingcompose.screen.bottomBar.order

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.model.response.OrderData
import com.example.onboardingcompose.model.response.OrderResponse
import com.example.onboardingcompose.ui.theme.gray
import com.example.onboardingcompose.ui.theme.lite_blue
import com.example.onboardingcompose.ui.theme.lite_blue2

@Composable
fun PendingScreen(apiServiceModel: ApiServiceModel, sharedViewModel: SharedViewModel, context: Context) {
    val pendingOrders = apiServiceModel.pendingOrders(sharedViewModel, context)

    Column(
        modifier = Modifier
            .background(color = lite_blue2)
            .fillMaxSize()
    ) {
        if (pendingOrders!=null) {
            LazyColumn {
                itemsIndexed(items = apiServiceModel.liveOrderData) { index, item ->
                    myCard(data = item)
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


