package com.example.inventario.presentation.components.viewscpu

import android.hardware.Camera.Area
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inventario.R

@Composable
fun CardsCPUs(navController: NavController) {
    val NoSerie = "2052Z32"
    val Responsable = "Responsable: Leonardo"
    val Area = "Area: Administracion"
    val Modelo = "Modelo: Optiplex 3090"
    Card(Modifier
        .fillMaxWidth()
        .height(140.dp)
        .padding(10.dp)
        .border(
            width = 2.dp,
            color = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )
        .clickable{
            navController.navigate("cpus")
        },
        shape = RoundedCornerShape(8.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row {
            Box(modifier = Modifier.fillMaxSize().weight(1f), contentAlignment = Alignment.Center) {
                Image(painter = painterResource(R.drawable.hp),
                    contentDescription = "Logo Dell",
                    modifier = Modifier.size(120.dp).padding(10.dp))
            }

            Column(Modifier
                .fillMaxSize()
                .weight(2f)
                .background(Color.Black)
                /*.drawBehind {
                    val strokeWidth = 3.dp.toPx()
                    val x = strokeWidth / 2 // ← Posición X (izquierda)
                    drawLine(
                        color = Color.Red,
                        start = Offset(x, 0f),
                        end = Offset(x, size.height),
                        strokeWidth = strokeWidth
                    )
                }*/
            ) {
                Text("2052Z32",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                        .drawBehind {
                            val strokeWidth = 3.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color.White,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        }
                )

                Text(Responsable)
                Text(Area)
                Text(Modelo)
            }
        }
    }
}