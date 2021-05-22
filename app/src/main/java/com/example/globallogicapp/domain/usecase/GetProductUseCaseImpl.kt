package com.example.globallogicapp.domain.usecase

import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.domain.repository.ProductRepository

/**
 * @author Axel Sanchez
 */
interface GetProductUseCase{
    suspend fun call(idProduct: Long): Product?
}

class GetProductUseCaseImpl(private val repository: ProductRepository): GetProductUseCase {
    override suspend fun call(idProduct: Long): Product? {
        return repository.getProduct(idProduct)
    }
}