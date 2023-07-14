package com.example.swipemvvmkoin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipemvvmkoin.model.ProductItem
import com.example.swipemvvmkoin.repository.SwipeApiRepository
import com.example.swipemvvmkoin.util.AppResult
import com.example.swipemvvmkoin.util.SingleLiveEvent
import kotlinx.coroutines.launch

class ProductListViewModel(private val repository: SwipeApiRepository) : ViewModel() {
    val showLoading = MutableLiveData<Boolean?>()
    val productList = MutableLiveData<List<ProductItem>?>()
    val showError = SingleLiveEvent<String?>()

    fun getAllProducts() {
        showLoading.value = true
        viewModelScope.launch {
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