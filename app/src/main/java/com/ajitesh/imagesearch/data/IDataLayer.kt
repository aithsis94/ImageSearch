package com.ajitesh.imagesearch.data

import android.content.Context
import com.ajitesh.imagesearch.model.ImageSearchResponse
import io.reactivex.Single

interface IDataLayer {
    fun fetchImageInfo(
        searchText: String,
        pageNumber: Int
    ): Single<ImageSearchResponse>
}