package com.example.inventario.data.network

import com.example.inventario.data.model.modelcpu.EquiposResponse
import com.example.inventario.data.model.modelcpu.StatusCPUResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EquiposApi {
    @GET("EquiposActivos")
    suspend fun getEquipos(): Response<EquiposResponse>

    // Para obtener un equipo específico por NoSerie
    @GET("PruebaEquipo/{noSerie}")
    suspend fun getEquipoByNoSerie(@Path("noSerie") noSerie: String): Response<EquiposResponse>

    //Para tomar el status de los CPU
    @GET("ConteoEquiposPorEstado")
    suspend fun  getStatusPC(): Response<StatusCPUResponse>
}