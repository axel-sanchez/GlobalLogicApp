package com.example.globallogicapp.di

import com.example.globallogicapp.data.repository.ProductRepositoryImpl
import com.example.globallogicapp.data.service.ApiService
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.data.source.ProductRemoteSourceImpl
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.domain.usecase.GetProductsUseCase
import com.example.globallogicapp.domain.usecase.GetProductsUseCaseImpl
import com.example.globallogicapp.helpers.Constants.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Axel Sanchez
 */
val moduleApp = module {
    single{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single{ (get() as Retrofit).create(ApiService::class.java) }

    single<ProductRepository> { ProductRepositoryImpl(get() as ProductRemoteSource) }
    single<ProductRemoteSource> { ProductRemoteSourceImpl(get() as ApiService) }
    single<GetProductsUseCase> { GetProductsUseCaseImpl(get() as ProductRepository) }
}