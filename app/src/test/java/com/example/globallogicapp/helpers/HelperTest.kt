package com.example.globallogicapp.helpers

import com.example.globallogicapp.data.model.Product

object HelperTest {
    val product1 = Product(
        1,
        "Description UNO",
        "https://picsum.photos/100/100?image=0",
        "Laptop 10"
    )

    val product2 = Product(
        2,
        "Description DOS",
        "https://picsum.photos/100/100?image=0",
        "Laptop 1"
    )

    val product3 = Product(
        3,
        "Description TRES",
        "https://picsum.photos/100/100?image=0",
        "Laptop 2"
    )

    val product4 = Product(
        4,
        "Description CUATRO",
        "https://picsum.photos/100/100?image=0",
        "Ace"
    )

    fun getListProducts(): Either<Constants.ApiError, List<Product?>> {
        val listProducts = arrayListOf<Product?>(product1, product2, product3, product4)
        return Either.Right(listProducts.toList())
    }
}