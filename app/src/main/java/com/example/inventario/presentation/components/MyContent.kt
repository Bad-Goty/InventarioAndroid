package com.example.inventario.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.inventario.presentation.components.cards.CardCPU
import com.example.inventario.presentation.components.cards.CardImpresora
import com.example.inventario.presentation.components.cards.CardMonitor
import com.example.inventario.presentation.components.cards.CardTabletTelefono
import com.example.inventario.presentation.components.cards.CardWifi

@Composable
fun MyContent(modifier: Modifier = Modifier, navController: NavController) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(
            MaterialTheme.colorScheme.primary
        )
    ) {

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            ContenedorCard {
                CardCPU(navController)
            }

            ContenedorCard {
                CardMonitor()
            }

            ContenedorCard {
                CardImpresora()
            }

            ContenedorCard {
                CardTabletTelefono()
            }

            ContenedorCard {
                CardWifi()
            }

        }
    }
}