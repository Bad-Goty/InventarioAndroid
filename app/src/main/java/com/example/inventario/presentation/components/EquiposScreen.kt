package com.example.inventario.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventario.data.model.modelcpu.CPUS
import com.example.inventario.presentation.viewmodel.EquiposViewModel


@Composable
fun EquiposScreen(viewModel: EquiposViewModel = viewModel()) {
    val equipos by viewModel.equipos
    val loading by viewModel.loading
    val error by viewModel.error

    // Cargar datos al iniciar
    LaunchedEffect(Unit) {
        viewModel.fetchEquipos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Botón para recargar
        Button(
            onClick = { viewModel.fetchEquipos() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recargar Equipos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                ) {
                    Text(
                        text = error!!,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            equipos.isEmpty() -> {
                Text("No hay equipos disponibles")
            }

            else -> {
                LazyColumn {
                    items(equipos) { equipo ->
                        EquipoCard(equipo = equipo)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipoCard(equipo: CPUS) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Serie: ${equipo.NoSerie}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Marca: ${equipo.Marca}")
                    Text("Modelo: ${equipo.Modelo}")
                    Text("RAM: ${equipo.RAM}")
                    Text("ROM: ${equipo.ROM}")
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text("CPU: ${equipo.CPU}")
                    Text("SO: ${equipo.SO}")
                    Text("IP: ${equipo.IP}")
                    Text("Área: ${equipo.Area}")
                }
            }
        }
    }
}