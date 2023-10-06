package com.example.dynamicfragments

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilderElectronics {

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://dummyjson.com/")
        .build()
        .create(ApiInterface::class.java)

}