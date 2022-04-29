package com.example.globallogicapp.di.component

import com.example.globallogicapp.di.module.ApplicationModule
import com.example.globallogicapp.presentation.DetailsFragment
import com.example.globallogicapp.presentation.ProductsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent{
    fun inject(detailsFragment: DetailsFragment)
    fun inject(productsFragment: ProductsFragment)
}