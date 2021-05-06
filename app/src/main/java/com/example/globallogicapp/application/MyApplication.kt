package com.example.globallogicapp.application

import android.app.Application
import com.example.globallogicapp.di.moduleApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author Axel Sanchez
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(moduleApp)
        }
    }
}