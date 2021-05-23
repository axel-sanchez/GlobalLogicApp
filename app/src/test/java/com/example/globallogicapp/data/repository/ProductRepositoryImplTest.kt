package com.example.globallogicapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.globallogicapp.data.source.ProductLocalSource
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Either
import com.example.globallogicapp.helpers.HelperTest.getListProducts
import com.example.globallogicapp.helpers.HelperTest.product1
import com.example.globallogicapp.helpers.HelperTest.product2
import com.example.globallogicapp.helpers.HelperTest.product3
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class ProductRepositoryImplTest {

    private val productRemoteSource: ProductRemoteSource = mock(ProductRemoteSource::class.java)
    private val productLocalSource: ProductLocalSource = mock(ProductLocalSource::class.java)
    private val productRepository: ProductRepository = ProductRepositoryImpl(productRemoteSource, productLocalSource)

    @Test
    fun should_return_product_list_sorted_by_title() {
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

    @Test
    fun should_calls_to_getRemoteProducts_when_there_are_not_local_products(){
        runBlocking {
            val mutableListData = MutableLiveData(getListProducts())
            given(productRemoteSource.getProducts()).willReturn(mutableListData)

            given(productLocalSource.insertProducts(product1)).willReturn(1)
            given(productLocalSource.insertProducts(product2)).willReturn(2)
            given(productLocalSource.insertProducts(product3)).willReturn(3)

            given(productRepository.getLocalProducts()).willReturn(listOf())

            productRepository.getAllProducts()
            verify(productRemoteSource).getProducts()
        }
    }

    @Test
    fun should_not_calls_to_getRemoteProducts_when_there_are_local_products(){
        runBlocking {
            val mutableListData = MutableLiveData(getListProducts())
            given(productRemoteSource.getProducts()).willReturn(mutableListData)

            given(productLocalSource.insertProducts(product1)).willReturn(1)
            given(productLocalSource.insertProducts(product2)).willReturn(2)
            given(productLocalSource.insertProducts(product3)).willReturn(3)

            given(productRepository.getLocalProducts()).willReturn(listOf(product1))

            productRepository.getAllProducts()
            verify(productRemoteSource, never()).getProducts()
        }
    }
}