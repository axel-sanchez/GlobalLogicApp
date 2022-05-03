package com.example.globallogicapp.domain.repository

import com.example.globallogicapp.data.model.Product

/**
 * @author Axel Sanchez
 */
interface ProductRepository {
    suspend fun getAllProducts(): List<Product?>?
    suspend fun getLocalProducts(): List<Product?>
    suspend fun getRemoteProducts(): List<Product?>?
    suspend fun getProduct(idProduct: Long): Product?
    suspend fun addProductsInDB(result: List<Product?>?)
}