package com.example.inventario.presentation.components.viewscpu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.ImeAction
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

    // Usar equiposFiltrados en lugar de equipos
    val equiposFiltrados by viewModel.equiposFiltrados
    val loading by viewModel.loading
    val error by viewModel.error
    val searchQuery by viewModel.searchQuery

    LaunchedEffect(Unit) {
        viewModel.fetchEquipos()
    }

    MyScaffold(
        Titutlo = "CPU",
        onFabClick = {
            navController.navigate("qr_scanner")
        }
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
                onMostrarBottomSheet = { mostrarBottomSheet = true },
                onSearch = { query ->
                    viewModel.searchEquipos(query)
                }
            )

            // Mostrar número de resultados
            if (searchQuery.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.9f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Resultados para: \"$searchQuery\"",
                            fontWeight = FontWeight.Medium
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "${equiposFiltrados.size} encontrados",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(
                                onClick = { viewModel.clearSearch() },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    Icons.Filled.Clear,
                                    contentDescription = "Limpiar búsqueda",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

            when {
                loading -> {
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

                equiposFiltrados.isEmpty() && searchQuery.isNotEmpty() -> {
                    // No hay resultados de búsqueda
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.9f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Filled.SearchOff,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp),
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "No se encontraron equipos",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                "Intenta con otro término de búsqueda",
                                fontSize = 14.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                equiposFiltrados.isEmpty() -> {
                    Text(
                        text = "No hay equipos disponibles",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }

                else -> {
                    // Mostrar los equipos filtrados
                    equiposFiltrados.forEach { equipo ->
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
fun CajaConBuscador(
    onMostrarBottomSheet: () -> Unit,
    onSearch: (String) -> Unit // ← Nueva función para búsqueda
) {
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
                placeholder = { Text("Buscar por Serie, Responsable o Área...") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search // ← Cambiar el botón del teclado a "Buscar"
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch(texto) // ← Ejecutar búsqueda al presionar el botón del teclado
                    }
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    onSearch(texto) // ← También buscar al presionar el botón
                },
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1C71C5),
                    contentColor = Color.White,
                    disabledContentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar",
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Botón de filtros
            Button(
                onClick = { onMostrarBottomSheet() },
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1C71C5),
                    contentColor = Color.White,
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.FilterList,
                    contentDescription = "Filtros",
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


