package com.example.globallogicapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.globallogicapp.data.repository.ProductRepositoryImpl
import com.example.globallogicapp.data.room.Database
import com.example.globallogicapp.data.service.ApiClient
import com.example.globallogicapp.data.service.ApiServiceProduct
import com.example.globallogicapp.data.source.ProductLocalSource
import com.example.globallogicapp.data.source.ProductLocalSourceImpl
import com.example.globallogicapp.data.source.ProductRemoteSource
import com.example.globallogicapp.data.source.ProductRemoteSourceImpl
import com.example.globallogicapp.domain.repository.ProductRepository
import com.example.globallogicapp.domain.usecase.GetAllProductsUseCase
import com.example.globallogicapp.domain.usecase.GetAllProductsUseCaseImpl
import com.example.globallogicapp.domain.usecase.GetProductUseCase
import com.example.globallogicapp.domain.usecase.GetProductUseCaseImpl
import com.example.globallogicapp.helpers.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Module
class ApplicationModule(private val context: Context){
    @Provides
    @Singleton
    fun provideGetProductUseCase(getProductUseCase: GetProductUseCaseImpl): GetProductUseCase = getProductUseCase

    @Provides
    @Singleton
    fun provideProductRepository(productRepository: ProductRepositoryImpl): ProductRepository = productRepository

    @Provides
    @Singleton
    fun provideProductRemoteSource(productRemoteSource: ProductRemoteSourceImpl): ProductRemoteSource = productRemoteSource

    @Provides
    @Singleton
    fun provideGetAllProductsUseCase(getAllProductsUseCase: GetAllProductsUseCaseImpl): GetAllProductsUseCase = getAllProductsUseCase

    @Provides
    @Singleton
    fun provideApiServiceProduct(): ApiServiceProduct{
        return ApiClient.Builder<ApiServiceProduct>()
            .setBaseUrl(BASE_URL)
            .setApiService(ApiServiceProduct::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): Database{
        return Room
            .databaseBuilder(context, Database::class.java, "GlobalLogicDB.db3")
            .build()
    }

    @Provides
    @Singleton
    fun provideProductLocalSource(database: Database): ProductLocalSource{
        return ProductLocalSourceImpl(database.productDao())
    }

    @Provides
    @Singleton
    fun provideContext(): Context = context
}