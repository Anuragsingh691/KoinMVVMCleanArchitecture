package com.example.swipemvvmkoin.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipemvvmkoin.model.ProductItem
import com.example.swipemvvmkoin.repository.SwipeApiRepository
import com.example.swipemvvmkoin.util.AppResult
import com.example.swipemvvmkoin.util.SingleLiveEvent
import kotlinx.coroutines.launch

class ProductListViewModel(private val repository: SwipeApiRepository) : ViewModel() {
    private val showLoading = ObservableBoolean()
    private val countriesList = MutableLiveData<List<ProductItem>?>()
    private val showError = SingleLiveEvent<String?>()

    fun getAllUsers() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getProducts()

            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    countriesList.value = result.successData
                    showError.value = null
                }

                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
}