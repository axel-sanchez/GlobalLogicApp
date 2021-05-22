package com.example.globallogicapp.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Axel Sanchez
 */
object ApiFactory {
    fun getApiClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}