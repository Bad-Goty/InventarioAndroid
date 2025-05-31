package com.example.inventario.presentation.components.viewscpu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.example.inventario.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inventario.presentation.components.MyScaffold

@Composable
fun DetalleCPU(navController: NavController) {
    val NoSerie = "2052Z32"
    MyScaffold(
        Titutlo = NoSerie,
        onFabClick = {}
    ) {innerPadding ->
        Column(Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(
                MaterialTheme.colorScheme.primary
            )
            .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(R.drawable.dell),
                contentDescription = "Dell",
                modifier = Modifier
                    .size(120.dp)
                    .padding(top = 10.dp)
            )

            Spacer(Modifier.height(15.dp))

            TextoCpu("Responsable: Leonardo")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Area: Administracion")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Modelo: Optiplex 3090")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Sistema: Windows 10 Pro")

            Spacer(Modifier.height(15.dp))
        }
    }
}

@Composable
fun TextoCpu(Content: String) {
    Text(Content,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 3.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
    )
}