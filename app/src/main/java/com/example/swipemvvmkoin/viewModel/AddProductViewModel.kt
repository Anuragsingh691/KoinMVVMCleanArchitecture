package com.example.swipemvvmkoin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipemvvmkoin.model.AddProductSuccessResponse
import com.example.swipemvvmkoin.model.ProductItem
import com.example.swipemvvmkoin.repository.SwipeApiRepository
import com.example.swipemvvmkoin.util.AppResult
import com.example.swipemvvmkoin.util.SingleLiveEvent
import kotlinx.coroutines.launch

class AddProductViewModel(private val repository: SwipeApiRepository) : ViewModel() {
    val showLoading = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<String?>()
    val addProductSuccessResponse = MutableLiveData<AddProductSuccessResponse?>()

    fun addProducts() {
        showLoading.value = true
        viewModelScope.launch {
            val result = repository.postProducts()

            showLoading.value = false
            when (result) {
                is AppResult.Success -> {
                    addProductSuccessResponse.value = result.successData
                    showError.value = null
                }

                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
}