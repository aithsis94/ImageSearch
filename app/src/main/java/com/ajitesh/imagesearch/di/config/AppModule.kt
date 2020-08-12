package com.ajitesh.imagesearch.di.config

import android.app.Application
import com.ajitesh.imagesearch.application.IDaggerApp
import com.ajitesh.imagesearch.data.DataAggregator
import com.ajitesh.imagesearch.data.IDataLayer
import com.ajitesh.imagesearch.data.network.INetworkModule
import com.ajitesh.imagesearch.data.network.RetrofitNetwork
import com.ajitesh.imagesearch.scheduler.ISchedulerProvider
import com.ajitesh.imagesearch.scheduler.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(val app: Application) {

    init {
        require(app is IDaggerApp) { "app should implement IDaggerApp" }
    }

    @Provides
    @Singleton
    open fun provideNetwork(): INetworkModule {
        return RetrofitNetwork
    }

    @Provides
    @Singleton
    open fun providesDataLayer(networkModule: INetworkModule): IDataLayer {
        return DataAggregator(app)
    }

    @Provides
    @Singleton
    open fun providesSchedulerProvider(): ISchedulerProvider = SchedulerProviderImpl()
}