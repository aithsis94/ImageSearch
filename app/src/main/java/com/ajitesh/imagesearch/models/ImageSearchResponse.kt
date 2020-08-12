package com.ajitesh.imagesearch.models

data class ImageSearchResponse(
    val stat: String,
    val photo: Photos
)

data class Photos(
    val perpage: Int,
    val total: Int,
    val pages: Int,
    val photo: List<Photo>,
    val page: Int
)

data class Photo(
    val owner: String,
    val server: String,
    val ispublic: String,
    val isfriend: String,
    val farm: String,
    val id: String,
    val secret: String,
    val title: String,
    val isfamily: String
)