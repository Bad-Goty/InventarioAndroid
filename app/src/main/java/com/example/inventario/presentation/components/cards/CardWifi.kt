package com.example.inventario.presentation.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventario.R

@Composable
fun CardWifi() {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent), // Fondo transparente
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
        ) {

            Box(
                Modifier
                .weight(1f)
                .fillMaxSize(),

                contentAlignment = Alignment.Center
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.wifi),
                    contentDescription = "CPU",
                    Modifier.size(120.dp).padding(10.dp)
                )

            }


            Box(modifier = Modifier
                .fillMaxSize()
                .weight(2f)
                .background(Color.Transparent),
            ) {

                Column(
                    Modifier
                    .fillMaxWidth(),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("Routers", Modifier.padding(top = 10.dp),fontWeight = FontWeight.Bold, fontSize = 30.sp)

                    Spacer(Modifier.height(10.dp))
                    Box(
                        Modifier
                        .fillMaxSize()
                    ){
                        Column {
                            Text("Activos:51", color = Color.White, fontSize = 20.sp)
                            Text("Inactivos:51", color = Color.White, fontSize = 20.sp)
                            Text("Mantenimiento:51", color = Color.White, fontSize = 20.sp)
                        }
                    }

                }
            }

        }
    }
}

