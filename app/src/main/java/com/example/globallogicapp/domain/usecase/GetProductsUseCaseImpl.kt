package com.example.globallogicapp.domain.usecase

import com.example.globallogicapp.data.model.Result
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either

/**
 * @author Axel Sanchez
 */
interface GetProductsUseCase{
    suspend fun call(): Either<Constants.ApiError, Result>
}

class GetProductsUseCaseImpl(private val repository: ProductRepository): GetProductsUseCase {
    override suspend fun call(): Either<Constants.ApiError, Result> {
        return repository.getProducts()
    }
}