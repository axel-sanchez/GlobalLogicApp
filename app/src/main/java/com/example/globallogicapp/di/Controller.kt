package com.example.globallogicapp.di

import androidx.room.Room
import com.example.globallogicapp.data.repository.ProductRepositoryImpl
import com.example.globallogicapp.data.room.Database
import com.example.globallogicapp.data.room.ProductDao
import com.example.globallogicapp.data.service.ApiServiceProduct
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.data.source.ProductRemoteSourceImpl
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.domain.usecase.GetAllProductsUseCase
import com.example.globallogicapp.domain.usecase.GetAllProductsUseCaseImpl
import com.example.globallogicapp.data.service.ApiFactory
import com.example.globallogicapp.data.source.ProductLocalSource
import com.example.globallogicapp.data.source.ProductLocalSourceImpl
import com.example.globallogicapp.domain.usecase.GetProductUseCase
import com.example.globallogicapp.domain.usecase.GetProductUseCaseImpl
import com.example.globallogicapp.helpers.Constants.BASE_URL
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Axel Sanchez
 */
val moduleApp = module {
    factory<ApiServiceProduct> { ApiFactory.getApiClient(BASE_URL).create(ApiServiceProduct::class.java) }
    single<ProductRepository> { ProductRepositoryImpl(get() as ProductRemoteSource, get() as ProductLocalSource) }
    single<ProductRemoteSource> { ProductRemoteSourceImpl(get() as ApiServiceProduct) }
    single<ProductLocalSource> { ProductLocalSourceImpl((get() as Database).productDao()) }
    single { Room
        .databaseBuilder(androidContext(), Database::class.java, "GlobalLogicDB.db3")
        .build() }
    single<GetAllProductsUseCase> { GetAllProductsUseCaseImpl(get() as ProductRepository) }
    single<GetProductUseCase> { GetProductUseCaseImpl(get() as ProductRepository) }
}