package com.example.inventario.presentation.views

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.inventario.presentation.components.MyContent
import androidx.navigation.NavController
import com.example.inventario.presentation.components.MyScaffold

@Composable
fun ContenedorPincipal(navController: NavController) {
    MyScaffold(
        Titutlo = "Inventario",
        onFabClick = {/*TODO*/},
    ) {innerPadding ->
        MyContent(Modifier.padding(innerPadding), navController)
    }
}
