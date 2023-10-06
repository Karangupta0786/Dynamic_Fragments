package com.example.dynamicfragments

import com.example.dynamicfragments.Data.GridData
import com.example.dynamicfragments.Data.ProductData
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {

    @GET("/posts")
    fun getGridData():Call<GridData>

    @GET("products")
    fun getProduct():Call<ProductData>


}
