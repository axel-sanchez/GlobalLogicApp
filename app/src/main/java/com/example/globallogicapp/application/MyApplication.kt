package com.example.globallogicapp.application

import android.app.Application
import com.example.globallogicapp.di.component.ApplicationComponent
import com.example.globallogicapp.di.component.DaggerApplicationComponent
import com.example.globallogicapp.di.module.ApplicationModule

/**
 * @author Axel Sanchez
 */
class MyApplication: Application() {
    val component: ApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
}