package com.example.mvvmprueba.ui.vistacliente.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmprueba.R
import kotlinx.coroutines.launch


@Composable
fun ClientScreen(viewModel: ClientViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF1c4c96))
    ) {
        Client(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Client(modifier: Modifier, viewModel: ClientViewModel) {

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val isContratoIn: Boolean by viewModel.isContratoIn.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if(isLoading){
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else if(isContratoIn){
        ContratosScreen()
    }else{

        Column(modifier = modifier) {
            HeaderImage(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(height = 200.dp, width = 200.dp)
            )
            Text(
                text = "CONDOSA",
                Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 50.dp), fontSize = 32.sp, color = Color.White
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ContratosButton {
                coroutineScope.launch {
                    viewModel.onContratoSelected()
                }
            }
            Spacer(modifier = Modifier.padding(75.dp))
        }
    }
}

@Composable
fun ContratosButton( onContratoSelected: () -> Unit) {
    Button(
        onClick = {onContratoSelected()},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(40.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Contratos")
    }
}


@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.condosa_1),
        contentDescription = "Header",
        modifier = modifier
    )
}