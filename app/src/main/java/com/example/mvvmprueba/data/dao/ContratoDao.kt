package com.example.mvvmprueba.data.dao

import com.example.mvvmprueba.data.models.Contrato
import com.example.mvvmprueba.data.models.Usuario
import com.example.mvvmprueba.data.tables.UsuarioTable
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import java.util.Date

val DEFAULT_FECHA: LocalDate = LocalDate.of(2023, 1, 1)
val DEFAULT_STRING: String ="HOLA"
class ContratoDao {



    fun probandoxd(): List<Contrato> {
        val result = mutableListOf<Contrato>()

        transaction {
            val query = ContratoTable.select { ContratoTable.fechaRegistro.isNull() }
            query.forEach {
                val idContrato = it[ContratoTable.idContrato]
                val idSolicitudCotizacion = it[ContratoTable.idSolicitudCotizacion]
                val idPersonal = it[ContratoTable.idPersonal]
                val idSolicitante = it[ContratoTable.idSolicitante]
                val fechaContrato = it[ContratoTable.fechaContrato]
                val fechaFirmaSolicitante = it[ContratoTable.fechaFirmaSolicitante]
                val fechaFirmaPersonal = it[ContratoTable.fechaFirmaPersonal]
                val fechaRegistro = it[ContratoTable.fechaRegistro]
                val minuta = it[ContratoTable.minuta]
                result.add(
                    Contrato(
                        idContrato,
                        idSolicitudCotizacion,
                        idPersonal,
                        idSolicitante,
                        fechaContrato,
                        fechaFirmaSolicitante ?: DEFAULT_FECHA,
                        fechaFirmaPersonal ?: DEFAULT_FECHA,
                        fechaRegistro ?: DEFAULT_FECHA,
                        minuta ?: DEFAULT_STRING
                    )
                )
            }
        }

        return result
    }


    fun getContratos(): List<Contrato> {
        val result = mutableListOf<Contrato>()

        transaction {
            val query = ContratoTable.select { ContratoTable.fechaRegistro.isNull() }
            query.forEach {
                val idContrato = it[ContratoTable.idContrato]
                val idSolicitudCotizacion = it[ContratoTable.idSolicitudCotizacion]
                val idPersonal = it[ContratoTable.idPersonal]
                val idSolicitante = it[ContratoTable.idSolicitante]
                val fechaContrato = it[ContratoTable.fechaContrato]
                val fechaFirmaSolicitante = it[ContratoTable.fechaFirmaSolicitante]
                val fechaFirmaPersonal = it[ContratoTable.fechaFirmaPersonal]
                val fechaRegistro = it[ContratoTable.fechaRegistro]
                val minuta = it[ContratoTable.minuta]

                result.add(
                    Contrato(
                        idContrato,
                        idSolicitudCotizacion,
                        idPersonal,
                        idSolicitante,
                        fechaContrato,
                        fechaFirmaSolicitante ?: DEFAULT_FECHA,
                        fechaFirmaPersonal ?: DEFAULT_FECHA,
                        fechaRegistro ?: DEFAULT_FECHA,
                        minuta ?: DEFAULT_STRING
                    )
                )
            }
        }

        return result
    }


    fun getContratosPendientes(): List<Triple<String, String, String>> {
        val todosContrato = getContratos()

        val contratosPendientesList = todosContrato
            .map {
                Triple(
                    it.idContrato.toString(),
                    it.idSolicitudCotizacion.toString(),
                    it.fechaContrato.toString()
                )
            }

        return contratosPendientesList
    }


}
