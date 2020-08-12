package com.ajitesh.imagesearch.util

sealed class Constants {

    companion object {

        const val BASE_URL_IMAGE_SEARCH = "https://api.flickr.com"
        const val PATH_IMAGE_SEARCH_SERVICE = "/services/rest"
        const val API_KEY = "062a6c0c49e4de1d78497d13a7dbb360"

        const val API_QUERY_VALUE_METHOD_SEARCH = "flickr.photos.search"
        const val API_QUERY_VALUE_FORMAT = "json"
        const val API_QUERY_VALUE_NO_JSON_CALLBACK = 1
        const val API_QUERY_VALUE_PAGE_SIZE = 10
    }
}