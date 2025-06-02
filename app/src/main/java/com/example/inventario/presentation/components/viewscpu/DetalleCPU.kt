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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inventario.presentation.components.MyScaffold

@Composable
fun DetalleCPU(navController: NavController) {
    val NoSerie = "2052Z32"
    MyScaffold(
        Titutlo = NoSerie,
        onFabClick = {},
        fabIcon = Icons.Filled.Edit,
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

            TextoCpu("Responsable", "Leonardo")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Area", "Administracion")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Modelo", "Optiplex 3090")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Formato equipo", "Escritorio")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Disco duro", "kingstone 550GB")

            Spacer(Modifier.height(15.dp))

            TextoCpu("RAM", "16GB")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Procesador", "12th Gen Intel(R) Core(TM) i3-12100   3.30 GHz")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Sesion", "DESARROLLOPC")

            Spacer(Modifier.height(15.dp))

            TextoCpu("S.O", "Windows 11 Pro")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Instalacion S.O", "21/05/2025")

            Spacer(Modifier.height(15.dp))

            TextoCpu("V. Office", "Microsoft Office LTSC Profesional Plus 2021")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Programas INT", "V, A")

            Spacer(Modifier.height(15.dp))

            TextoCpu("Unidad de Red", "P, S, Y")

            Spacer(Modifier.height(15.dp))

            TextoCpu("IP", "192.168.2.42")

        }
    }
}

@Composable
fun TextoCpu(label: String, value: String) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFF1C71C5), fontWeight = FontWeight.Bold)) {
            append(label)
        }
        withStyle(style = SpanStyle(color = Color.Black)) {
            append(": $value")
        }
    }

    Text(
        text = annotatedString,
        fontSize = 25.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

