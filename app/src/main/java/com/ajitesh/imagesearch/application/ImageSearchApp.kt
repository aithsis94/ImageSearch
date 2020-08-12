package com.ajitesh.imagesearch.application

import android.app.Application
import com.ajitesh.imagesearch.di.config.AppModule
import com.ajitesh.imagesearch.di.config.DaggerIAppComponent
import com.ajitesh.imagesearch.di.config.IAppComponent

open class ImageSearchApp : Application(), IDaggerApp {

    protected var appComponent: IAppComponent? = null
    protected val lock = Object()

    override fun getComponent(): IAppComponent {

        synchronized(lock) {
            if (appComponent == null) {
                appComponent = DaggerIAppComponent.builder().appModule(getAppModule()).build()
            }
            return appComponent!!
        }
    }

    open protected fun getAppModule(): AppModule {
        return AppModule(this)
    }
}