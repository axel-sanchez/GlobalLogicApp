package com.example.globallogicapp.domain.repository

import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either

/**
 * @author Axel Sanchez
 */
interface ProductRepository {
    suspend fun getAllProducts(): Either<Constants.ApiError, List<Product?>>
    suspend fun getProduct(idProduct: Long): Product?
}