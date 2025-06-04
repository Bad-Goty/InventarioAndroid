package com.example.inventario.presentation.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.inventario.R
import com.example.inventario.data.model.modelcpu.StatusCPU
import com.example.inventario.presentation.viewmodel.EquiposViewModel

@Composable
fun CardCPU(navController: NavController) {

    val equiposViewModel: EquiposViewModel = viewModel()
    val statusList by equiposViewModel.statuspc

    LaunchedEffect(Unit) {
        equiposViewModel.fetchStatusPC()
    }

    fun getCantidadByDescripcion(lista: List<StatusCPU>, descripcion: String): Comparable<*> {
        return lista.find { it.descripcion.equals(descripcion, ignoreCase = true) }?.cantidad ?: 0
    }

    Card(
        modifier = Modifier.fillMaxSize()
            .clickable{
                navController.navigate("cpu")
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {

            Box(Modifier
                .weight(1f)
                .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cpu),
                    contentDescription = "CPU",
                    Modifier.size(120.dp).padding(10.dp)
                )
            }

            Box(modifier = Modifier
                .fillMaxSize()
                .weight(2f)
                .background(Color.Transparent),
            ) {
                Column(Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("CPU", Modifier.padding(top = 10.dp),fontWeight = FontWeight.Bold, fontSize = 30.sp)

                    Spacer(Modifier.height(10.dp))
                    Box(Modifier.fillMaxSize()){
                        Column {
                            Text(
                                "Activos: ${getCantidadByDescripcion(statusList, "Activos")}",
                                color = Color.White,
                                fontSize = 20.sp
                            )
                            Text(
                                "Inactivos: ${getCantidadByDescripcion(statusList, "Inactivos")}",
                                color = Color.White,
                                fontSize = 20.sp
                            )
                            Text(
                                "Mantenimiento: ${getCantidadByDescripcion(statusList, "Mantenimiento")}",
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

