package com.example.mvvmprueba.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmprueba.R
import com.example.mvvmprueba.ui.vistacliente.ui.ClientScreen
import com.example.mvvmprueba.ui.vistacliente.ui.ClientViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF9ab4ff))
    ) {
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val isLoggedIn: Boolean by viewModel.isLoggedIn.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else if(isLoggedIn){
        ClientScreen(ClientViewModel())
    } else {
        Column(modifier = modifier) {
            HeaderImage(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(height = 200.dp, width = 200.dp))
            Text(text="CONDOSA",
                Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 50.dp), fontSize = 32.sp, color = Color.White)
            Spacer(modifier = Modifier.padding(4.dp))
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnable) {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
            Spacer(modifier = Modifier.padding(75.dp))
        }
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(40.dp,0.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Iniciar sesión")
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false)}
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth().padding(30.dp,0.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true, //Solo permite escribir en una linea
        maxLines = 1, // Acompaña para que el numero de lineas no crezca
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon   = {
            val imagen = if(passwordVisibility){
                Icons.Filled.VisibilityOff
            }else{
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = imagen, contentDescription = "Show password")
            }
        },
        visualTransformation = if(passwordVisibility){
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth().padding(30.dp,0.dp),
        placeholder = { Text(text = "Nombre de usuario") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.condosa_1),
        contentDescription = "Header",
        modifier = modifier
    )
}