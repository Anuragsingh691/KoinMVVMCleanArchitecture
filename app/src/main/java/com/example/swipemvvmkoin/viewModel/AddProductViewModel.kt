package com.example.swipemvvmkoin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipemvvmkoin.model.AddProductSuccessResponse
import com.example.swipemvvmkoin.model.ProductItem
import com.example.swipemvvmkoin.model.ProductRequest
import com.example.swipemvvmkoin.repository.SwipeApiRepository
import com.example.swipemvvmkoin.util.AppResult
import com.example.swipemvvmkoin.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddProductViewModel(private val repository: SwipeApiRepository) : ViewModel() {
    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String?>()
    val showSuccessToast = MutableLiveData<Boolean?>()

    fun addProducts(productRequest: ProductRequest) {
        showLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.postProducts(productRequest)) {
                is AppResult.Success -> {
                    showLoading.value = false
                    showSuccessToast.value = true
                }

                is AppResult.Error -> {
                    showLoading.value = false
                    showError.value = result.exception.message
                    showSuccessToast.value = false}
            }
        }
    }
}