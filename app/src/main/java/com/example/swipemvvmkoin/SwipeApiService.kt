package com.example.swipemvvmkoin

import com.example.swipemvvmkoin.model.AddProductSuccessResponse
import com.example.swipemvvmkoin.model.ProductItem
import com.example.swipemvvmkoin.model.ProductRequest
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SwipeApiService {
    @GET("get")
    suspend fun getProducts(): Response<List<ProductItem>>

    @POST("add")
    suspend fun addProducts(@Body request: ProductRequest): Response<AddProductSuccessResponse>
}