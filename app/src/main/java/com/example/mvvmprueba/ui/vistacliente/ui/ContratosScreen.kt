package com.example.mvvmprueba.ui.vistacliente.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mvvmprueba.data.dao.executeQuery
import com.example.mvvmprueba.data.models.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

@Composable
fun ContratosScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF1c4c96))
    ) {
        UserDataList()
    }
}

@Composable
fun UserDataList() {
    var userData by remember { mutableStateOf<List<Usuario>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Utiliza un scope de corrutina para ejecutar la consulta en un hilo de fondo
        val result = runBlocking(Dispatchers.IO) {
            executeQuery()
        }

        // Actualiza los datos en el hilo principal
        userData = result
    }

    Column {
        Text("Lista de usuarios:")
        Spacer(modifier = Modifier.height(8.dp))

        userData.forEach { user ->
            Text("${user.idUsuario}, ${user.userName}, ${user.password}")
        }
    }
}