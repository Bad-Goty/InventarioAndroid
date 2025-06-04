package com.example.inventario.presentation.components.viewscpu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.example.inventario.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inventario.data.model.modelcpu.CPUS
import com.example.inventario.presentation.components.MyScaffold
import com.example.inventario.presentation.viewmodel.EquiposViewModel

@Composable
fun DetalleCPU(
    navController: NavController,
    viewModel: EquiposViewModel,
    noSerie: String
) {
    val equipoDetalle by viewModel.equipoDetalle
    val loading by viewModel.loading
    val error by viewModel.error

    LaunchedEffect(noSerie) {
        if (noSerie.isNotEmpty()) {
            viewModel.fetchEquipoByNoSerie(noSerie)
        }
    }

    MyScaffold(
        Titutlo = noSerie,
        onFabClick = {},
        fabIcon = Icons.Filled.Edit,
    ) { innerPadding ->
        when {
            loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }

            error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Error: $error",
                        color = Color.Red,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            equipoDetalle != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.primary)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // ← AQUÍ ESTÁ EL FIX: Manejo seguro de null
                    Image(
                        painter = painterResource(
                            when (equipoDetalle!!.Marca?.lowercase()) { // ← Agrega ?
                                "dell" -> R.drawable.dell
                                "hp" -> R.drawable.hp
                                else -> R.drawable.dell // Por defecto si es null
                            }
                        ),
                        contentDescription = equipoDetalle!!.Marca ?: "Equipo", // ← Manejo de null
                        modifier = Modifier
                            .size(120.dp)
                            .padding(top = 10.dp)
                    )

                    Spacer(Modifier.height(15.dp))

                    // Uso seguro de los campos que pueden ser null
                    TextoCpu("Responsable", equipoDetalle!!.Responsable?.toString() ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("Area", equipoDetalle!!.Area ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("Modelo", equipoDetalle!!.Modelo ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("Formato equipo", equipoDetalle!!.FormatoEquipo ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("Disco duro", equipoDetalle!!.ROM ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("RAM", equipoDetalle!!.RAM ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("Procesador", equipoDetalle!!.CPU ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("Sesion", equipoDetalle!!.Sesion ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("S.O", equipoDetalle!!.SO ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("Instalacion S.O", equipoDetalle!!.InstalacionSO ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("V. Office", equipoDetalle!!.Voffice ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("Programas INT", equipoDetalle!!.ProgramasInt ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("Unidad de Red", equipoDetalle!!.UniRed ?: "No definido")

                    Spacer(Modifier.height(15.dp))

                    TextoCpu("IP", equipoDetalle!!.IP ?: "No definido")

                    Spacer(Modifier.height(20.dp))
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No se encontró información del equipo",
                        color = Color.White,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
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