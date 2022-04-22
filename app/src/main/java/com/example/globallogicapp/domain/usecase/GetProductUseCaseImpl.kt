package com.example.globallogicapp.domain.usecase

import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface GetProductUseCase{
    suspend fun call(idProduct: Long): Product?
}

@Singleton
class GetProductUseCaseImpl @Inject constructor(private val repository: ProductRepository): GetProductUseCase {
    override suspend fun call(idProduct: Long): Product? {
        return repository.getProduct(idProduct)
    }
}