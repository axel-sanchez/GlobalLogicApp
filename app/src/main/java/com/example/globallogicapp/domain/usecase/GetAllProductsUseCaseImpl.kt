package com.example.globallogicapp.domain.usecase

import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface GetAllProductsUseCase{
    suspend fun call(): List<Product?>?
}

@Singleton
class GetAllProductsUseCaseImpl @Inject constructor(private val repository: ProductRepository): GetAllProductsUseCase {
    override suspend fun call(): List<Product?>? {
        return repository.getAllProducts()
    }
}