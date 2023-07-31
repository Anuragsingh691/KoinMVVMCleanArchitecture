package com.example.swipemvvmkoin.repository

import com.example.swipemvvmkoin.model.AddProductSuccessResponse
import com.example.swipemvvmkoin.model.ProductItem
import com.example.swipemvvmkoin.model.ProductRequest
import com.example.swipemvvmkoin.util.AppResult

interface SwipeApiRepository2 : SwipeApiRepository{
    suspend fun getProducts2() : AppResult<List<ProductItem>>
}