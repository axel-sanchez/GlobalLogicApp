package com.example.globallogicapp.data.service

import com.example.globallogicapp.data.model.Product
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Axel Sanchez
 */
interface ApiServiceProduct{
    @GET("list")
    suspend fun getProducts(): Response<List<Product?>?>
}