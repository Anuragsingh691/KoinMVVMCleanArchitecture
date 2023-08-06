package com.example.swipemvvmkoin.viewModel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipemvvmkoin.model.ProductItem
import com.example.swipemvvmkoin.repository.SwipeApiRepository
import com.example.swipemvvmkoin.util.AppResult
import com.example.swipemvvmkoin.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class ProductListViewModel(private val repository: SwipeApiRepository) : ViewModel() {
    val showLoading = MutableLiveData<Boolean?>()
    val productList = MutableLiveData<List<ProductItem>?>()
    val showError = SingleLiveEvent<String?>()
    private val lifeCycleState = MutableSharedFlow<Lifecycle.State>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    fun getAllProducts() {
        showLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getProducts()
            showLoading.value = false
            when (result) {
                is AppResult.Success -> {
                    productList.value = result.successData
                    showError.value = null
                }

                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }

}