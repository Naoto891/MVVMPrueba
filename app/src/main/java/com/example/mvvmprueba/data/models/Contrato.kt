package com.example.mvvmprueba.data.models
import java.time.LocalDate

data class Contrato(
    val idContrato: Int,
    val idSolicitudCotizacion: Int,
    val idPersonal: Int,
    val idSolicitante: Int,
    val fechaContrato: LocalDate,
    val fechaFirmaSolicitante: LocalDate,
    val fechaFirmaPersonal: LocalDate,
    val fechaRegistro: LocalDate,
    val minuta: String
)
