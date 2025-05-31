package com.example.inventario.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ContenedorCard(contenido: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(200.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF3E6032), Color(0xFF93B78D)), // de oscuro (abajo) a claro (arriba)
                    end = Offset(0f, Float.POSITIVE_INFINITY), // Abajo
                    start = Offset(0f, 0f)                          // Arriba
                )
                ,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        contenido()
    }
}