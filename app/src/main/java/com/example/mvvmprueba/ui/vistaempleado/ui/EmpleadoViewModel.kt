package com.example.mvvmprueba.ui.vistaempleado.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject
@HiltViewModel
class EmpleadoViewModel @Inject constructor() : ViewModel(){

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isContratoIn = MutableLiveData<Boolean>()
    val isContratoIn: LiveData<Boolean> = _isContratoIn


    suspend fun onContratoSelected() {
        _isLoading.value = true
        delay(1000)
        _isContratoIn.value = true
        _isLoading.value = false
    }

}