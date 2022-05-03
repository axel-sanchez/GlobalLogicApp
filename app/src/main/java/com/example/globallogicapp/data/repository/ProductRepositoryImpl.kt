package com.example.globallogicapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.data.model.ResultListProducts
import com.example.globallogicapp.data.source.ProductLocalSource
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Constants.Error.*
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
    override suspend fun getAllProducts(): ResultListProducts {

        val localProducts = getLocalProducts()
        if (localProducts.isNotEmpty()) {
            return ResultListProducts(localProducts, NoError)
        }

        val resultRemoteProducts = getRemoteProducts()

        if (resultRemoteProducts.error is NoError) {
            addProductsInDB(resultRemoteProducts.listProducts)
            val sortedList = resultRemoteProducts.listProducts?.sortedWith(
                compareBy(
                    { it?.title?.substringBeforeLast(" ") }, { it?.title?.substringAfterLast(" ")?.toInt() }
                )
            )
            resultRemoteProducts.listProducts = sortedList
            return resultRemoteProducts
        }

        return resultRemoteProducts
    }

    override suspend fun getLocalProducts(): List<Product?> {
        return productLocalSource.getAllProducts()
    }

    override suspend fun getRemoteProducts(): ResultListProducts {
        return productRemoteSource.getProducts().value ?: ResultListProducts(null, UnknownError())
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