package com.example.mvvmprueba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mvvmprueba.ui.login.ui.LoginScreen
import com.example.mvvmprueba.ui.login.ui.LoginViewModel
import com.example.mvvmprueba.ui.theme.MVVMPruebaTheme
import com.example.mvvmprueba.config.DatabaseConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DatabaseConfig.init() // Inicializa la base de datos

        setContent {

            MVVMPruebaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(LoginViewModel())
                }
            }
        }
    }
}

