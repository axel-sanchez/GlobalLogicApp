package com.example.globallogicapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.globallogicapp.data.model.Product
import com.example.globallogicapp.data.source.ProductLocalSource
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.helpers.Constants
import com.example.globallogicapp.helpers.Either
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class ProductRepositoryImplTest {

    private val productRemoteSource: ProductRemoteSource = mock(ProductRemoteSource::class.java)
    private val productLocalSource: ProductLocalSource = mock(ProductLocalSource::class.java)

    @Test
    fun should_return_list_sorted() = runBlocking {
        foo()
    }

    private suspend fun foo() {

        val productRepository: ProductRepository = ProductRepositoryImpl(productRemoteSource, productLocalSource)

        coroutineScope {
            launch {
                given(productRepository.getLocalProducts()).willReturn(listOf())

               //given(productRepository.addProductsInDB(listOf())).willReturn(Unit)

                val mutableListData = MutableLiveData(getListProducts())
                given(productRemoteSource.getProducts()).willReturn(mutableListData)

                given(productLocalSource.insertProducts(
                    Product(
                        1,
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis dapibus varius sem, eu ultricies urna condimentum a. Suspendisse aliquam mi vel orci viverra consectetur. Morbi at diam neque. Nam commodo risus sit amet mi hendrerit, sed facilisis quam tincidunt. Nulla facilisi. Nam commodo ultricies dignissim. In tempor sapien mattis, suscipit dolor at, semper odio. Proin interdum sapien sit amet nibh tincidunt, luctus congue nunc viverra. Proin pharetra neque vel orci porta, et pharetra turpis venenatis. Nam volutpat aliquet ante, nec ullamcorper felis semper eget. Vivamus posuere suscipit gravida. Nunc ut efficitur turpis.",
                        "https://picsum.photos/100/100?image=0",
                        "Laptop 1"
                    )
                )).willReturn(1)

                val result = productRepository.getAllProducts()
                assertTrue((result as Either.Right).r.isNotEmpty())
            }
        }
    }

    private fun getListProducts(): Either<Constants.ApiError, List<Product?>> {
        val listProducts = arrayListOf<Product?>()
        listProducts.add(
            Product(
                1,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis dapibus varius sem, eu ultricies urna condimentum a. Suspendisse aliquam mi vel orci viverra consectetur. Morbi at diam neque. Nam commodo risus sit amet mi hendrerit, sed facilisis quam tincidunt. Nulla facilisi. Nam commodo ultricies dignissim. In tempor sapien mattis, suscipit dolor at, semper odio. Proin interdum sapien sit amet nibh tincidunt, luctus congue nunc viverra. Proin pharetra neque vel orci porta, et pharetra turpis venenatis. Nam volutpat aliquet ante, nec ullamcorper felis semper eget. Vivamus posuere suscipit gravida. Nunc ut efficitur turpis.",
                "https://picsum.photos/100/100?image=0",
                "Laptop 1"
            )
        )
        return Either.Right(listProducts.toList())
    }

}