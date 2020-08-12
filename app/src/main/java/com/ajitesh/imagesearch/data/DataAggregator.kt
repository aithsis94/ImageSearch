package com.ajitesh.imagesearch.data

import android.app.Application
import com.ajitesh.imagesearch.application.IDaggerApp
import com.ajitesh.imagesearch.data.network.INetworkModule
import com.ajitesh.imagesearch.model.ImageSearchResponse
import io.reactivex.Single
import javax.inject.Inject

//This class can be used to fetch data from localdb when internet is down.
//Since the offline functionality is not a part of this assignment, im skipping that
//But using this architecture we can easily integrate with local db and fetch data from it when the network in not available
class DataAggregator(app: Application) : IDataLayer {

    var network: INetworkModule? = null
        @Inject set

    init {
        if (app is IDaggerApp) {
            app.getComponent().injectDataAggregator(this)
        }
    }

    override fun fetchImageInfo(
        searchText: String,
        pageNumber: Int
    ): Single<ImageSearchResponse> {

        return network!!.getImageSearchApi()
            .searchImageByKeyword(searchText, pageNumber)
    }
}