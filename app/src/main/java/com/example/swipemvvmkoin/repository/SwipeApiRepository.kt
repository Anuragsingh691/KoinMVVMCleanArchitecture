package com.example.swipemvvmkoin.repository

import com.example.swipemvvmkoin.model.AddProductSuccessResponse
import com.example.swipemvvmkoin.model.ProductItem
import com.example.swipemvvmkoin.util.AppResult

interface SwipeApiRepository {
    suspend fun getProducts() : AppResult<List<ProductItem>>

    suspend fun postProducts() : AppResult<AddProductSuccessResponse>
}