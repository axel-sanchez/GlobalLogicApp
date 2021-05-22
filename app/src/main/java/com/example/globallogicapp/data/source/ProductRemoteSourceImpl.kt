package com.example.globallogicapp.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.globallogicapp.data.model.Result
import com.example.globallogicapp.data.service.ApiServiceProduct
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Constants.ApiError.*
import com.example.globallogicapp.helpers.Either

/**
 * @author Axel Sanchez
 */
interface ProductRemoteSource {
    suspend fun getProducts(): MutableLiveData<Either<Constants.ApiError, Result>>
}

class ProductRemoteSourceImpl(private val service: ApiServiceProduct) : ProductRemoteSource {
    override suspend fun getProducts(): MutableLiveData<Either<Constants.ApiError, Result>> {
        val mutableLiveData = MutableLiveData<Either<Constants.ApiError, Result>>()

        try {
            val response = service.getProducts()
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body().toString())

                response.body()?.let { lyric ->
                    mutableLiveData.value = Either.Right(lyric)
                } ?: kotlin.run {
                    mutableLiveData.value = Either.Left(API_ERROR)
                }
            } else {
                Log.i("Error Response", response.errorBody().toString())
                val apiError = API_ERROR
                apiError.error = response.message()
                mutableLiveData.value = Either.Left(apiError)
            }
        } catch (e: Exception) {
            mutableLiveData.value = Either.Left(API_ERROR)
            Log.e(
                "ProductRemoteSourceImpl",
                e.message?:"Error al obtener los productos"
            )
            e.printStackTrace()
        }

        return mutableLiveData
    }
}