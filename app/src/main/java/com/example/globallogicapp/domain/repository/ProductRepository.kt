package com.example.globallogicapp.domain.repository

import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.data.model.ResultListProducts
import com.example.globallogicapp.helpers.Constants

/**
 * @author Axel Sanchez
 */
interface ProductRepository {
    suspend fun getAllProducts(): ResultListProducts
    suspend fun getLocalProducts(): List<Product?>
    suspend fun getRemoteProducts(): ResultListProducts
    suspend fun getProduct(idProduct: Long): Product?
    suspend fun addProductsInDB(result: List<Product?>?)
}