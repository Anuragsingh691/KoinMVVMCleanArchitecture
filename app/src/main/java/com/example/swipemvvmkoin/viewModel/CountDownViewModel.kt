package com.example.swipemvvmkoin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class CountDownViewModel : ViewModel() {
    var time =  MutableLiveData<Int>()
    private val countDown = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    fun collectFlow() {
        viewModelScope.launch {
            countDown.collectLatest { timer ->
                time.value = timer
            }
        }
    }
}