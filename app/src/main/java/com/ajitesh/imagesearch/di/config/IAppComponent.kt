package com.ajitesh.imagesearch.di.config

import com.ajitesh.imagesearch.data.DataAggregator
import com.ajitesh.imagesearch.viewmodel.factory.AppViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface IAppComponent {
    fun injectAppViewModelFactory(appViewModelFactory: AppViewModelFactory)
    fun injectDataAggregator(dataAggregator: DataAggregator)
}