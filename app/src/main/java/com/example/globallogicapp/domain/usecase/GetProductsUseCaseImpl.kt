package com.example.globallogicapp.domain.usecase

import com.example.globallogicapp.data.model.Result
import com.example.globallogicapp.data.source.ApiError
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Either

/**
 * @author Axel Sanchez
 */
interface GetProductsUseCase{
    suspend fun call(): Either<ApiError, Result>
}

class GetProductsUseCaseImpl(private val repository: ProductRepository): GetProductsUseCase {
    override suspend fun call(): Either<ApiError, Result> {
        return repository.getProducts()
    }
}