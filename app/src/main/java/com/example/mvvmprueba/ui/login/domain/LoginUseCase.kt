package com.example.mvvmprueba.ui.login.domain

import com.example.mvvmprueba.data.dao.UsuarioDao
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val usuarioDao: UsuarioDao) {

    operator fun invoke(username: String, password: String): Boolean {

        return usuarioDao.checkUserCredentials(username, password)
    }
}
