package com.ajitesh.imagesearch.network

import com.ajitesh.imagesearch.constants.Constants
import com.ajitesh.imagesearch.models.ImageSearchResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageSearchApi {

    @GET(Constants.PATH_IMAGE_SEARCH_SERVICE)
    fun searchImageByKeyword(
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("format") format: String = Constants.API_QUERY_VALUE_FORMAT,
        @Query("per_page") pageSize : Int = Constants.API_QUERY_VALUE_PAGE_SIZE,
        @Query("nojsoncallback") noJsonCallback : Int = Constants.API_QUERY_VALUE_NO_JSON_CALLBACK,
        @Query("method") method : String = Constants.API_QUERY_VALUE_METHOD_SEARCH
    ): Single<ImageSearchResponse>
}


object RetrofitNetwork : INetworkModule {

    override fun getImageSearchApi(): ImageSearchApi {
        return IMAGE_SEARCH_SEARCH_API
    }

    private val IMAGE_SEARCH_SEARCH_API: ImageSearchApi

    init {

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_IMAGE_SEARCH)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().let {
                IMAGE_SEARCH_SEARCH_API = it.create(ImageSearchApi::class.java)
            }
    }
}