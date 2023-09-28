package com.example.mvvmprueba.ui.vistacliente.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class ClientViewModel : ViewModel(){

  //  private val _contratoEnable = MutableLiveData<Boolean>()
   // val contratoEnable: LiveData<Boolean> = _contratoEnable

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