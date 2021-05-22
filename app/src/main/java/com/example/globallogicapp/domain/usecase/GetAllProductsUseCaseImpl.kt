package com.example.globallogicapp.domain.usecase

import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either

/**
 * @author Axel Sanchez
 */
interface GetAllProductsUseCase{
    suspend fun call(): Either<Constants.ApiError, List<Product?>>
}

class GetAllProductsUseCaseImpl(private val repository: ProductRepository): GetAllProductsUseCase {
    override suspend fun call(): Either<Constants.ApiError, List<Product?>> {
        return repository.getAllProducts()
    }
}