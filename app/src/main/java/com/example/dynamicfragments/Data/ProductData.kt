package com.example.dynamicfragments.Data

data class ProductData(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)