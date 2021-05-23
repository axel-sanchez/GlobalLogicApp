package com.example.globallogicapp.helpers

import com.example.globallogicapp.data.model.Product

object HelperTest {
    val product1 = Product(
        1,
        "Description UNO",
        "https://picsum.photos/100/100?image=0",
        "Lenovo"
    )

    val product2 = Product(
        2,
        "Description DOS",
        "https://picsum.photos/100/100?image=0",
        "Ace"
    )

    val product3 = Product(
        3,
        "Description TRES",
        "https://picsum.photos/100/100?image=0",
        "HP"
    )

    fun getListProducts(): Either<Constants.ApiError, List<Product?>> {
        val listProducts = arrayListOf<Product?>(product1, product2, product3)
        return Either.Right(listProducts.toList())
    }
}