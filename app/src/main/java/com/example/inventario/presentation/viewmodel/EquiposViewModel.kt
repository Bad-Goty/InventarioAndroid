package com.example.inventario.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventario.data.model.modelcpu.CPUS
import com.example.inventario.data.network.RetrofitClient
import kotlinx.coroutines.launch

class EquiposViewModel : ViewModel() {
    private val _equipos = mutableStateOf<List<CPUS>>(emptyList())
    val equipos: State<List<CPUS>> = _equipos

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun fetchEquipos(){
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = RetrofitClient.equiposApi.getEquipos()

                if (response.isSuccessful){
                    _equipos.value = response.body()?.equipos ?: emptyList()
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception){
                _error.value = "Error de conexion: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}