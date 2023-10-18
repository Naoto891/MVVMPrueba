package com.example.mvvmprueba.data.dao

import com.example.mvvmprueba.data.models.Contrato
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

val DEFAULT_FECHA: LocalDate = LocalDate.of(2023, 1, 1)
const val DEFAULT_STRING: String =""
class ContratoDao {

    fun getContratos(alternarTab : Boolean): List<Contrato> {
        val result = mutableListOf<Contrato>()

        transaction {

            var query = ContratoTable.select { ContratoTable.fechaRegistro.isNull() }

            if(alternarTab == true){
                query = ContratoTable.select { ContratoTable.fechaRegistro.isNotNull() }
            }

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

}
