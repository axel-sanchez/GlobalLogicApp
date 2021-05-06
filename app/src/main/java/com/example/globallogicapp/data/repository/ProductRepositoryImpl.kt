package com.example.globallogicapp.data.repository

import com.example.globallogicapp.data.Result
import com.example.globallogicapp.data.source.ApiError
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Either

/**
 * @author Axel Sanchez
 */
class ProductRepositoryImpl(private val productRemoteSource: ProductRemoteSource): ProductRepository {
    override suspend fun getProducts(): Either<ApiError, Result> {
        return productRemoteSource.getProducts().value ?: Either.Left(ApiError.API_ERROR)
    }
}