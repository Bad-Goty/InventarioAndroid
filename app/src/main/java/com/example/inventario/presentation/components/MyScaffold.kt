package com.example.inventario.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(
    Titutlo: String,
    onFabClick: () -> Unit = {},
    fabIcon: ImageVector = Icons.Filled.QrCode2, // Ícono por defecto
    showFab: Boolean = true,
    content: @Composable (PaddingValues) -> Unit
) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF1C71C5)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons =  false //Esto hace blanco la hora, wifi y todo
        )
    }

    Scaffold(modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = { Text(Titutlo, Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 35.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = statusBarColor,
                    titleContentColor = Color.White
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick, // ← CAMBIO AQUÍ: usar el parámetro
                containerColor = Color(0xFF1C71C5)
            ) {
                Icon(imageVector = fabIcon, contentDescription = "Buscar QR", Modifier.size(35.dp))
            }
        }

    ) { innerPadding ->
        content(innerPadding)
    }

}