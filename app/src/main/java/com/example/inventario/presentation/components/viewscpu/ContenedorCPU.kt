package com.example.inventario.presentation.components.viewscpu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inventario.presentation.components.MyScaffold
import com.example.inventario.presentation.viewmodel.EquiposViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenedorCPU(navController: NavController, viewModel: EquiposViewModel) {
    var mostrarBottomSheet by remember { mutableStateOf(false) }

    // Obtener los datos del ViewModel
    val equipos by viewModel.equipos
    val loading by viewModel.loading
    val error by viewModel.error

    // Cargar datos al entrar a la pantalla
    LaunchedEffect(Unit) {
        viewModel.fetchEquipos()
    }

    MyScaffold(
        Titutlo = "CPU",
        onFabClick = {/*TODO*/},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.primary)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CajaConBuscador(
                onMostrarBottomSheet = { mostrarBottomSheet = true }
            )

            when {
                loading -> {
                    // Mostrar indicador de carga
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }
                }

                error != null -> {
                    // Mostrar error
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Red.copy(alpha = 0.1f)
                        )
                    ) {
                        Text(
                            text = "Error: $error",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                equipos.isEmpty() -> {
                    // No hay datos
                    Text(
                        text = "No hay equipos disponibles",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }

                else -> {
                    // Mostrar los equipos reales (reemplaza el for loop)
                    equipos.forEach { equipo ->
                        CardsCPUs(navController, equipo)
                    }
                }
            }
        }
    }

    // Bottom Sheet
    if (mostrarBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { mostrarBottomSheet = false },
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = false
            ),
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            ContenidoDelBottomSheet()
        }
    }
}

@Composable
fun CajaConBuscador(onMostrarBottomSheet: () -> Unit) {
    var texto by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Row(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            OutlinedTextField(
                value = texto,
                onValueChange = { texto = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Buscar...") },
                singleLine = true, //←evita el salto de línea
                colors = OutlinedTextFieldDefaults.colors(

                    cursorColor = Color.Black,

                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = Color.White,

                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,

                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {  onMostrarBottomSheet() },
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(0.dp), // para que el ícono no tenga relleno extra
                shape = RoundedCornerShape(20.dp),
                colors =  ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1C71C5),
                    contentColor = Color.White,
                    disabledContentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar QR",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

// Contenido del Bottom Sheet
@Composable
fun ContenidoDelBottomSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        for (a in 0 .. 20)
        Text("Filtros",
            Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold)
    }
}


