package com.ajitesh.imagesearch.model

open class ImageSearchResponse(
    val stat: String,
    val photos: PhotoPageInfo
)

open class PhotoPageInfo(
    val perpage: Int,
    val total: Int,
    val pages: Int,
    val photo: List<PhotoInfo>,
    val page: Int
)

open class PhotoInfo(
    val owner: String,
    val server: String,
    val ispublic: String,
    val isfriend: String,
    val farm: String,
    val id: String,
    val secret: String,
    val title: String,
    val isfamily: String,
    var url: String? = null
)