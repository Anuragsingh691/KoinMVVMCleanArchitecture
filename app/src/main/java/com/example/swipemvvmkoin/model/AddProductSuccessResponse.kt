package com.example.swipemvvmkoin.model

data class AddProductSuccessResponse(
    val message: String,
    val product_details: ProductItem,
    val product_id: Int,
    val success: Boolean
)