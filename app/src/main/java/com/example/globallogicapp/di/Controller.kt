package com.example.globallogicapp.di

import com.example.globallogicapp.data.repository.ProductRepositoryImpl
import com.example.globallogicapp.data.service.ApiServiceProduct
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.data.source.ProductRemoteSourceImpl
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.domain.usecase.GetProductsUseCase
import com.example.globallogicapp.domain.usecase.GetProductsUseCaseImpl
import com.example.globallogicapp.data.service.ApiFactory
import com.example.globallogicapp.helpers.Constants.BASE_URL
import org.koin.dsl.module

/**
 * @author Axel Sanchez
 */
val moduleApp = module {
    factory<ApiServiceProduct> { ApiFactory.getApiClient(BASE_URL).create(ApiServiceProduct::class.java) }
    single<ProductRepository> { ProductRepositoryImpl(get() as ProductRemoteSource) }
    single<ProductRemoteSource> { ProductRemoteSourceImpl(get() as ApiServiceProduct) }
    single<GetProductsUseCase> { GetProductsUseCaseImpl(get() as ProductRepository) }
}