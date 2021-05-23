package com.example.globallogicapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.data.source.ProductLocalSource
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class ProductRepositoryImplTest {

    private val product1 = Product(
        1,
        "Description UNO",
        "https://picsum.photos/100/100?image=0",
        "Lenovo"
    )

    private val product2 = Product(
        2,
        "Description DOS",
        "https://picsum.photos/100/100?image=0",
        "Ace"
    )

    private val product3 = Product(
        3,
        "Description TRES",
        "https://picsum.photos/100/100?image=0",
        "HP"
    )

    private val productRemoteSource: ProductRemoteSource = mock(ProductRemoteSource::class.java)
    private val productLocalSource: ProductLocalSource = mock(ProductLocalSource::class.java)

    @Test
    fun should_return_product_list_sorted_by_title() {
        val productRepository: ProductRepository = ProductRepositoryImpl(productRemoteSource, productLocalSource)

        runBlocking {
            given(productRepository.getLocalProducts()).willReturn(listOf())

            val mutableListData = MutableLiveData(getListProducts())
            given(productRemoteSource.getProducts()).willReturn(mutableListData)

            given(productLocalSource.insertProducts(product1)).willReturn(1)
            given(productLocalSource.insertProducts(product2)).willReturn(2)
            given(productLocalSource.insertProducts(product3)).willReturn(3)

            val result = productRepository.getAllProducts()
            assertThat((result as Either.Right).r, contains(product2, product3,product1))
        }
    }

    private fun getListProducts(): Either<Constants.ApiError, List<Product?>> {
        val listProducts = arrayListOf<Product?>(product1, product2, product3)
        return Either.Right(listProducts.toList())
    }
}