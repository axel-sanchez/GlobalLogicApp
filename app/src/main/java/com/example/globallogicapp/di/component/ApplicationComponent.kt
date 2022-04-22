package com.example.globallogicapp.di.component

import com.example.globallogicapp.di.module.ApplicationModule
import com.example.globallogicapp.ui.DetailsFragment
import com.example.globallogicapp.ui.ProductsFragment
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