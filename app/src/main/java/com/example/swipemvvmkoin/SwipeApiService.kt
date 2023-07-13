package com.example.swipemvvmkoin

import com.example.swipemvvmkoin.model.ProductItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SwipeApiService {
    @GET("get")
    suspend fun getProducts(): Response<List<ProductItem>>

//    companion object {
//        private var retrofitService: SwipeApiService? = null
//        fun getInstance(): SwipeApiService {
//            if (retrofitService == null) {
//                val retrofit = Retrofit.Builder()
//                    .baseUrl("https://app.getswipe.in/api/public/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                retrofitService = retrofit.create(SwipeApiService::class.java)
//            }
//            return retrofitService!!
//        }
//
//    }
}