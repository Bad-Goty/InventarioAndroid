package com.example.inventario.presentation.views

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.inventario.presentation.components.MyContent
import androidx.navigation.NavController
import com.example.inventario.presentation.components.MyScaffold
import com.example.inventario.presentation.viewmodel.EquiposViewModel

@Composable
fun ContenedorPincipal(navController: NavController) {
    MyScaffold(
        Titutlo = "Inventario",
        onFabClick = {navController.navigate("qr_scanner")},
    ) {innerPadding ->
        MyContent(Modifier.padding(innerPadding), navController)
    }
}
