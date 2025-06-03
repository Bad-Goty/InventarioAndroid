package com.example.inventario.data.model.modelcpu

import com.google.gson.annotations.SerializedName

data class CPUS (
    @SerializedName("NoSerie")
    val NoSerie: String,
    val Responsable: Int,
    val Factura: String?,
    val FormatoEquipo: String,
    val Marca: String,
    val Modelo: String,
    val ROM: String,
    val RAM: String,
    val CPU: String,
    val Sesion: String,
    val SO: String,
    val InstalacionSO: String ,
    val Voffice: String,
    val ProgramasInt: String,
    val UniRed: String,
    val IP: String,
    val Area: String
)