package com.example.mvvmprueba.data.tables

import org.jetbrains.exposed.sql.Table

object UsuarioTable : Table("usuario") {
    val idUsuario = integer("id_usuario").autoIncrement()
    val userName = varchar("user_name", length = 255)
    val password = varchar("password", length = 255)

    override val primaryKey = PrimaryKey(idUsuario)
}
