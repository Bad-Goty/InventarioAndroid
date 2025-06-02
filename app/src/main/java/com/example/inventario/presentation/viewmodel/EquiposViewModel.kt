package com.example.inventario.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.inventario.data.model.modelcpu.CPUS

class EquiposViewModel : ViewModel() {
    private val _equipos = mutableStateOf<List<CPUS>>(emptyList())
    val equipos: State
}