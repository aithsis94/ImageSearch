package com.ajitesh.imagesearch.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajitesh.imagesearch.application.IDaggerApp
import com.ajitesh.imagesearch.data.IDataLayer
import com.ajitesh.imagesearch.scheduler.ISchedulerProvider
import com.ajitesh.imagesearch.viewmodel.MainViewModel
import javax.inject.Inject

class AppViewModelFactory(val app: Application) : ViewModelProvider.AndroidViewModelFactory(app) {

    var dataLayer: IDataLayer? = null
        @Inject set

    var schedulerProvider: ISchedulerProvider? = null
        @Inject set

    init {
        if (this.app is IDaggerApp) {
            app.getComponent().injectAppViewModelFactory(this)
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if (modelClass == MainViewModel::class.java) {
            MainViewModel(this.app, this.dataLayer!!, schedulerProvider!!) as T
        } else {
            super.create(modelClass)
        }
    }
}