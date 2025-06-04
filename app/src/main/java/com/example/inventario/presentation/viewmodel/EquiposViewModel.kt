package com.example.inventario.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventario.data.model.modelcpu.CPUS
import com.example.inventario.data.model.modelcpu.StatusCPU
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


    private val _equipoDetalle = mutableStateOf<CPUS?>(null)
    val equipoDetalle: State<CPUS?> = _equipoDetalle

    // Función para obtener un equipo específico
    fun fetchEquipoByNoSerie(noSerie: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                Log.d("EquiposViewModel", "Buscando equipo: $noSerie")
                val response = RetrofitClient.equiposApi.getEquipoByNoSerie(noSerie)

                if (response.isSuccessful) {
                    val equiposResponse = response.body()
                    // Tomar el primer equipo de la lista (debería ser solo uno)
                    val equipo = equiposResponse?.equipos?.firstOrNull()
                    _equipoDetalle.value = equipo

                    if (equipo == null) {
                        _error.value = "No se encontró el equipo"
                    }
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Error de conexión: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _equiposFiltrados = mutableStateOf<List<CPUS>>(emptyList())
    val equiposFiltrados: State<List<CPUS>> = _equiposFiltrados

    init {
        // Inicialmente mostrar todos los equipos
        _equiposFiltrados.value = _equipos.value
    }

    fun searchEquipos(query: String) {
        _searchQuery.value = query

        if (query.isEmpty()) {
            // Si no hay búsqueda, mostrar todos
            _equiposFiltrados.value = _equipos.value
        } else {
            // Filtrar por NoSerie, Responsable o Area
            _equiposFiltrados.value = _equipos.value.filter { equipo ->
                equipo.NoSerie?.contains(query, ignoreCase = true) == true ||
                        equipo.Responsable?.toString()?.contains(query, ignoreCase = true) == true ||
                        equipo.Area?.contains(query, ignoreCase = true) == true
            }
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _equiposFiltrados.value = _equipos.value
    }

    private val _statuspc = mutableStateOf<List<StatusCPU>>(emptyList())
    val statuspc: State<List<StatusCPU>> = _statuspc



    fun fetchStatusPC(){
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = RetrofitClient.equiposApi.getStatusPC()

                if (response.isSuccessful){
                    _statuspc.value = response.body()?.conteoPorEstado ?: emptyList()
                } else {
                    _error.value = "Errpr ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception){
                _error.value = "Error de conexion: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

}