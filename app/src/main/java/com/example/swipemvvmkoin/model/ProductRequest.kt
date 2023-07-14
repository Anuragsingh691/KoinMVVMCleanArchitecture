package com.example.swipemvvmkoin.model

import com.google.gson.annotations.SerializedName

data class ProductRequest(
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_type")
    val product_type: String,
    @SerializedName("tax")
    val tax: Double
)