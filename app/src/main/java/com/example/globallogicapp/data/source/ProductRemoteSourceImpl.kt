package com.example.globallogicapp.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.data.service.ApiServiceProduct
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Constants.ApiError.API_ERROR
import com.example.globallogicapp.helpers.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface ProductRemoteSource {
    suspend fun getProducts(): MutableLiveData<Either<Constants.ApiError, List<Product?>>>
}

@Singleton
class ProductRemoteSourceImpl @Inject constructor(private val service: ApiServiceProduct) : ProductRemoteSource {
    override suspend fun getProducts(): MutableLiveData<Either<Constants.ApiError, List<Product?>>> {
        val mutableLiveData = MutableLiveData<Either<Constants.ApiError, List<Product?>>>()

        try {
            val response = service.getProducts()
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body().toString())

                response.body()?.let { products ->
                    mutableLiveData.value = Either.Right(products)
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