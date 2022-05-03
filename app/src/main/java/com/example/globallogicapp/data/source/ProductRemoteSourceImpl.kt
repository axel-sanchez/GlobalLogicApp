package com.example.globallogicapp.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.data.service.ApiServiceProduct
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface ProductRemoteSource {
    suspend fun getProducts(): MutableLiveData<List<Product?>?>
}

@Singleton
class ProductRemoteSourceImpl @Inject constructor(private val service: ApiServiceProduct) : ProductRemoteSource {
    override suspend fun getProducts(): MutableLiveData<List<Product?>?> {
        val mutableLiveData = MutableLiveData<List<Product?>?>()

        try {
            val response = service.getProducts()
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body().toString())

                response.body()?.let { products ->
                    mutableLiveData.value = products
                } ?: kotlin.run {
                    mutableLiveData.value = null
                }
            } else {
                Log.i("Error Response", response.errorBody().toString())
                mutableLiveData.value = null
            }
        } catch (e: Exception) {
            mutableLiveData.value = null
            Log.e("ProductRemoteSourceImpl", e.message?:"Error al obtener los productos")
            e.printStackTrace()
        }

        return mutableLiveData
    }
}