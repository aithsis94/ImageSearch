package com.ajitesh.imagesearch.di.config

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface IAppComponent {

}