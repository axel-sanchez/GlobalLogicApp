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

        val localProducts = getLocalProducts()
        if (localProducts.isNotEmpty()) {
            return Either.Right(localProducts)
        }

        val eitherRemoteProducts = getRemoteProducts()

        if (eitherRemoteProducts is Either.Right) {
            addProductsInDB(eitherRemoteProducts.r)
            val sortedList = eitherRemoteProducts.r.sortedBy { it?.title }
            return Either.Right(sortedList)
        }

        return eitherRemoteProducts
    }

    override suspend fun getLocalProducts(): List<Product?> {
        return productLocalSource.getAllProducts()
    }

    override suspend fun getRemoteProducts(): Either<Constants.ApiError, List<Product?>> {
        return productRemoteSource.getProducts().value ?: Either.Left(Constants.ApiError.API_ERROR)
    }

    override suspend fun getProduct(idProduct: Long): Product? {
        return productLocalSource.getProduct(idProduct)
    }

    override suspend fun addProductsInDB(result: List<Product?>) {
        result.forEach { product ->
            product?.id = productLocalSource.insertProducts(product)
        }
    }
}