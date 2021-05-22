package com.example.globallogicapp.data.repository

import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.data.source.ProductLocalSource
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either

/**
 * @author Axel Sanchez
 */
class ProductRepositoryImpl(
    private val productRemoteSource: ProductRemoteSource,
    private val productLocalSource: ProductLocalSource
) : ProductRepository {
    override suspend fun getAllProducts(): Either<Constants.ApiError, List<Product?>> {

        val localProducts = productLocalSource.getAllProducts()

        if (localProducts.isNotEmpty()) {
            return Either.Right(localProducts)
        }

        val eitherRemoteProducts = productRemoteSource.getProducts().value ?: Either.Left(Constants.ApiError.API_ERROR)

        if (eitherRemoteProducts is Either.Right) {
            addProductsInDB(eitherRemoteProducts.r)
        }
        return eitherRemoteProducts
    }

    override suspend fun getProduct(idProduct: Long): Product? {
        return productLocalSource.getProduct(idProduct)
    }

    private suspend fun addProductsInDB(result: List<Product?>) {
        result.forEach { product ->
            product?.id = productLocalSource.insertProducts(product)
        }
    }
}