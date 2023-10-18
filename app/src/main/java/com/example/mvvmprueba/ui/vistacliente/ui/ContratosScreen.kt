package com.example.mvvmprueba.ui.vistacliente.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.mvvmprueba.data.dao.ContratoDao
import com.example.mvvmprueba.data.dao.UsuarioDao
import com.example.mvvmprueba.data.models.Contrato
import com.example.mvvmprueba.data.models.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


object AppContainer {
    val usuarioDao: UsuarioDao by lazy { UsuarioDao() }
}

object AppContainer2 {
    val contratoDao: ContratoDao by lazy { ContratoDao() }
}
// ContratosScreen
@Composable
fun ContratosScreen() {

    Box (
        Modifier
            .fillMaxSize()
            .background(Color(0xFF1c4c96)),
        contentAlignment = Alignment.TopCenter
    ) {
        UserDataList(Modifier)
    }
}
@Composable
fun UserDataList(modifier: Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val list = listOf("Contratos Pendientes", "Contratos Completados")


    Column(modifier = modifier) {
        HeaderImage(
            Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(height = 200.dp, width = 200.dp)
                .padding(top = 50.dp)
        )
        Text(
            text = "CONDOSA",
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(bottom = 50.dp),
            fontSize = 32.sp,
            color = Color.White
        )

        // Agrega dos pestañas debajo del texto
        TabRow(selectedTabIndex = selectedIndex,

            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clip(RoundedCornerShape(15))
                .padding(1.dp),
            indicator = { tabPositions: List<TabPosition> ->
                Box {}
            }
        ) {
            list.forEachIndexed { index, text ->
                val selected = selectedIndex == index
                Tab(
                    modifier = if (selected) Modifier
                        .clip(RoundedCornerShape(15))
                        .background(Color(0xff062863))
                    else Modifier
                        .clip(RoundedCornerShape(15))
                        .background(Color(0xff607EC9)),
                    selected = selected,
                    onClick = { selectedIndex = index },
                    text = { Text(text = text, color = Color.White)
                    }
                )
            }

        }

        TableScreen()

    }
}


@Composable
fun UserDataList() {
    var userData by remember { mutableStateOf<List<Usuario>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Utiliza un scope de corrutina para ejecutar la consulta en un hilo de fondo
        val result = runBlocking(Dispatchers.IO) {
            AppContainer.usuarioDao.executeQuery()
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

@Composable
fun ContratosList(){
    var contratoData by remember { mutableStateOf<List<Contrato>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Utiliza un scope de corrutina para ejecutar la consulta en un hilo de fondo
        val result = runBlocking(Dispatchers.IO) {
            AppContainer2.contratoDao.probandoxd()
        }

        // Actualiza los datos en el hilo principal
        contratoData = result
    }

    Column {
        Text("Lista de usuarios:")
        Spacer(modifier = Modifier.height(8.dp))

        contratoData.forEach { cont ->
            Text("${cont.idContrato}, ${cont.idSolicitudCotizacion}, ${cont.fechaContrato}")
        }
    }

}

/*
@Composable
fun probando() {
    val usuarios by remember {
        mutableStateOf(AppContainer.usuarioDao.getAllUsuarios())
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(usuarios) { usuario ->
            UserListItem(usuario = usuario)
        }
    }
}

@Composable
fun UserListItem(usuario: Usuario) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "ID: ${usuario.idUsuario}")
        Text(text = "Username: ${usuario.userName}")
        Text(text = "Password: ${usuario.password}")
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

*/


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TableScreen() {
    // Define weights for each column
    val column1Weight = .25f
    val column2Weight = .25f
    val column3Weight = .28f
    val column4Weight = .22f

    var contratoData by remember { mutableStateOf<List<Contrato>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Utiliza un scope de corrutina para ejecutar la consulta en un hilo de fondo
        val result = runBlocking(Dispatchers.IO) {
            AppContainer2.contratoDao.probandoxd()
        }

        // Actualiza los datos en el hilo principal
        contratoData = result
    }

    Scaffold(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, bottom = 50.dp),
        containerColor  = Color(0xff9AB4FF)
    ) {
        // LazyColumn for the table
        LazyColumn(Modifier.padding(8.dp)) {
            // Title row
            item {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableCell(text = "ID Contrato", weight = column1Weight, title = true)
                    TableCell(text = "ID Cotizacion", weight = column2Weight, title = true)
                    TableCell(text = "F Creacion", weight = column3Weight, title = true)
                    TableCell(text = "Accion", weight = column4Weight, title = true)
                }

                // Divider line
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }

            // Data rows
            itemsIndexed(contratoData) { _, contrato ->
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableCell(text = contrato.idContrato.toString(), weight = column1Weight)
                    TableCell(text = contrato.idSolicitudCotizacion.toString(), weight = column2Weight)
                    StatusCell(text = contrato.fechaContrato.toString(), weight = column3Weight)
                    TableCell(text = "holi", weight = column4Weight)
                }

                // Divider line
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    alignment: TextAlign = TextAlign.Center,
    title: Boolean = false
) {
    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(10.dp),
        fontWeight = if (title) FontWeight.Bold else FontWeight.Normal,
        textAlign = alignment,
    )
}

@Composable
fun RowScope.StatusCell(
    text: String,
    weight: Float,
    alignment: TextAlign = TextAlign.Center,
) {

    val color = when (text) {
        "Pending" -> Color(0xfff8deb5)
        "Paid" -> Color(0xffadf7a4)
        else -> Color(0xffffcccf)
    }
    val textColor = when (text) {
        "Pending" -> Color(0xffde7a1d)
        "Paid" -> Color(0xff00ad0e)
        else -> Color(0xffca1e17)
    }

    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(12.dp)
            .background(color, shape = RoundedCornerShape(50.dp)),
        textAlign = alignment,
        color = textColor
    )
}



@Preview(showBackground = true)
@Composable
fun TableScreenPreview() {
    ContratosScreen()
}
