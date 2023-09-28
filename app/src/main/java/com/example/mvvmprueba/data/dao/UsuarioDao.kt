package com.example.mvvmprueba.data.dao

import com.example.mvvmprueba.data.models.Usuario
import com.example.mvvmprueba.data.models.UsuarioTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun executeQuery(): List<Usuario> {
    val result = mutableListOf<Usuario>()

    transaction {
        val query = UsuarioTable.selectAll()
        query.forEach {
            val idUsuario = it[UsuarioTable.idUsuario]
            val userName = it[UsuarioTable.userName]
            val password = it[UsuarioTable.password]
            result.add(Usuario(idUsuario, userName, password))
        }
    }

    return result
}