package com.example.swipemvvmkoin.viewModel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.lang.Exception

class CountDownViewModel : ViewModel() {
    var time = MutableLiveData<Int>()
    private val _stateFlow = MutableStateFlow(0)
    private val stateFlow = _stateFlow.asStateFlow()
    val state = MutableLiveData<Int>()
    private val _sharedFlow = MutableSharedFlow<Int>()
    private val sharedFlow = _sharedFlow.asSharedFlow()
    val shared = MutableLiveData<Int>()
    var error = MutableLiveData<String>()
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

    fun squaredNumber(number: Int) {
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }

    fun incrementCounter() {
        _stateFlow.value += 1;
    }

    fun collectStateFlow() {
        viewModelScope.launch {
            stateFlow.collectLatest {
                state.value = it
            }
        }
    }

    fun collectSharedFlow() {
        viewModelScope.launch {
            sharedFlow.collect {
                shared.value = it
            }
        }
    }

    fun collectFlow() {
        viewModelScope.launch {
            countDown
                .filter {
                    it % 2 == 0
                }
                .map {
                    it * it
                }
                .collect { timer ->
                    time.value = timer
                }
        }
    }

    fun handleExceptionInCoroutine() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            error.value = throwable.message.toString()
        }
        viewModelScope.launch(Dispatchers.Main + handler) {
            supervisorScope {
                launch {
                    delay(300L)
                    throw Exception("C1 failed")
                }
                launch {
                    delay(400L)
                    error.value = "supervisor example happened"
                }
            }
        }
    }
}