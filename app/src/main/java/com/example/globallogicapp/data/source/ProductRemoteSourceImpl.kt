package com.example.globallogicapp.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.globallogicapp.data.model.ResultListProducts
import com.example.globallogicapp.data.service.ApiServiceProduct
import com.example.globallogicapp.helpers.Constants.Error
import com.example.globallogicapp.helpers.Constants.Error.*
import java.lang.NullPointerException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface ProductRemoteSource {
    suspend fun getProducts(): MutableLiveData<ResultListProducts>
}

@Singleton
class ProductRemoteSourceImpl @Inject constructor(private val service: ApiServiceProduct) : ProductRemoteSource {
    override suspend fun getProducts(): MutableLiveData<ResultListProducts> {
        val mutableLiveData = MutableLiveData<ResultListProducts>()

        try {
            val response = service.getProducts()
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body().toString())

                response.body()?.let { products ->
                    mutableLiveData.value = ResultListProducts(products, NoError)
                } ?: kotlin.run {
                    mutableLiveData.value = ResultListProducts(null, UnknownError())
                }
            } else {
                Log.i("Error Response", response.errorBody().toString())
                mutableLiveData.value = ResultListProducts(null, ApiError(response.message()))
            }
        } catch (e: Exception) {
            mutableLiveData.value = ResultListProducts(null, AppError(e.message?:"Error al obtener los productos"))
            Log.e("ProductRemoteSourceImpl", e.message?:"Error al obtener los productos")
            e.printStackTrace()
        }

        return mutableLiveData
    }
}