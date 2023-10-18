package com.example.mvvmprueba.data.dao

import com.example.mvvmprueba.data.models.Usuario
import com.example.mvvmprueba.data.tables.UsuarioTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class UsuarioDao  @Inject constructor(){

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


    fun getAllUsuarios(): List<Usuario> {
        return runBlocking {
            withContext(Dispatchers.IO) {
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

                result
            }
        }
    }


    fun checkUserCredentials(username: String, password: String): Boolean {
        var userExists = false

        transaction {
            val query = UsuarioTable.select { (UsuarioTable.userName eq username) and (UsuarioTable.password eq password) }
            userExists = query.count() > 0
        }

        return userExists
    }
}
