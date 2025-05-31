package com.example.inventario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.EnterTransition
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventario.presentation.components.viewscpu.ContenedorCPU
import com.example.inventario.presentation.components.viewscpu.DetalleCPU
import com.example.inventario.presentation.ui.theme.InventarioTheme
import com.example.inventario.presentation.views.ContenedorPincipal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventarioTheme {

                // Crear el NavController
                val navController = rememberNavController()

                // Configurar la navegación
                NavHost(
                    navController = navController,
                    startDestination = "home",

                    // Quitar todas las animaciones
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None }
                ) {
                    composable("home") {
                        ContenedorPincipal(navController)
                    }


                    composable("cpu") {
                        ContenedorCPU(navController)
                    }


                    composable("cpus") {
                        DetalleCPU(navController)
                    }


                }

            }
        }
    }
}
