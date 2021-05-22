package com.example.globallogicapp.data.source

import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.data.room.ProductDao

/**
 * @author Axel Sanchez
 */
interface ProductLocalSource {
    suspend fun getAllProducts(): List<Product?>
    suspend fun getProduct(idProduct: Long): Product?
    suspend fun insertProducts(product: Product?): Long
}

class ProductLocalSourceImpl(private val database: ProductDao): ProductLocalSource{
    override suspend fun getAllProducts(): List<Product?> {
        return database.getAllProducts()
    }

    override suspend fun insertProducts(product: Product?): Long {
        return database.insertProduct(product)
    }

    override suspend fun getProduct(idProduct: Long): Product? {
        return database.getProduct(idProduct)
    }
}