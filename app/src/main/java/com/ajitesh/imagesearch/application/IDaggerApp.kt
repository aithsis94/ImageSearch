package com.ajitesh.imagesearch.application

import com.ajitesh.imagesearch.di.config.IAppComponent

interface IDaggerApp {
    fun getComponent(): IAppComponent
}