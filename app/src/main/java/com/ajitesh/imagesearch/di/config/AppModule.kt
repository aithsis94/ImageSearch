package com.ajitesh.imagesearch.di.config

import com.ajitesh.imagesearch.network.INetworkModule
import com.ajitesh.imagesearch.network.RetrofitNetwork
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    @Singleton
    open fun provideNetwork(): INetworkModule {
        return RetrofitNetwork
    }
}