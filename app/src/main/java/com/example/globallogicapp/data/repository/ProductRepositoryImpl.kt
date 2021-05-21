package com.example.globallogicapp.data.repository

import com.example.globallogicapp.data.model.Result
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either

/**
 * @author Axel Sanchez
 */
class ProductRepositoryImpl(private val productRemoteSource: ProductRemoteSource): ProductRepository {
    override suspend fun getProducts(): Either<Constants.ApiError, Result> {
        return productRemoteSource.getProducts().value ?: Either.Left(Constants.ApiError.API_ERROR)
    }
}