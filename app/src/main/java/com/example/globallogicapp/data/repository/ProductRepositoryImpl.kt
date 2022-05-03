package com.example.globallogicapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.data.source.ProductLocalSource
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productRemoteSource: ProductRemoteSource,
    private val productLocalSource: ProductLocalSource
) : ProductRepository {
    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getAllProducts(): List<Product?>? {

        val localProducts = getLocalProducts()
        if (localProducts.isNotEmpty()) {
            return localProducts
        }

        var remoteProducts = getRemoteProducts()

        if (remoteProducts?.isNotEmpty() == true) {
            addProductsInDB(remoteProducts)
            val sortedList = remoteProducts.sortedWith(
                compareBy(
                    { it?.title?.substringBeforeLast(" ") }, { it?.title?.substringAfterLast(" ")?.toInt() }
                )
            )
            remoteProducts = sortedList
            return remoteProducts
        }

        return remoteProducts
    }

    override suspend fun getLocalProducts(): List<Product?> {
        return productLocalSource.getAllProducts()
    }

    override suspend fun getRemoteProducts(): List<Product?>? {
        return productRemoteSource.getProducts().value
    }

    override suspend fun getProduct(idProduct: Long): Product? {
        return productLocalSource.getProduct(idProduct)
    }

    override suspend fun addProductsInDB(result: List<Product?>?) {
        result?.forEach { product ->
            product?.id = productLocalSource.insertProducts(product)
        }
    }
}