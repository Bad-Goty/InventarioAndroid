package com.example.inventario.data.network

import com.example.inventario.data.model.modelcpu.EquiposResponse
import retrofit2.Response
import retrofit2.http.GET

interface EquiposApi {
    @GET("EquiposActivos")
    suspend fun getEquipos(): Response<EquiposResponse>
}