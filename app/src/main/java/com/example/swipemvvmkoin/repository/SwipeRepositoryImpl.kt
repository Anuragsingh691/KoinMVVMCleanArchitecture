package com.example.swipemvvmkoin.repository

import com.example.swipemvvmkoin.util.Utils
import android.content.Context
import com.example.swipemvvmkoin.SwipeApiService
import com.example.swipemvvmkoin.model.AddProductSuccessResponse
import com.example.swipemvvmkoin.model.ProductItem
import com.example.swipemvvmkoin.model.ProductRequest
import com.example.swipemvvmkoin.util.AppResult

class SwipeRepositoryImpl(private val api: SwipeApiService, private val context: Context) :
    SwipeApiRepository {

    override suspend fun getProducts(): AppResult<List<ProductItem>> {
        return try {
            val response = api.getProducts()
            if (response.isSuccessful) {
                Utils.handleSuccess(response)
            } else {
                Utils.handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun postProducts(productRequest: ProductRequest): AppResult<AddProductSuccessResponse> {
        return try {
            val response = api.addProducts(productRequest)
            if (response.isSuccessful) {
                Utils.handleSuccess(response)
            } else {
                Utils.handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }


}