package com.example.onboardingcompose.screen.bottomBar.order

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onboardingcompose.model.response.OrderData


@Composable
fun myCard(data: OrderData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.dp),
        elevation = 0.dp,
        backgroundColor = Color.White
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "${"# " + data.id}",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = data.createdAt.toString(),
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(
                    text = data.details.toString(),
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Text(
                    text = data.work?.name.toString(),
                    color = Color.Blue,
                    fontSize = 14.sp
                )
            }
        }
    }
}