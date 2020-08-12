package com.ajitesh.imagesearch.viewmodel

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SingleLiveEvent
import com.ajitesh.imagesearch.data.IDataLayer
import com.ajitesh.imagesearch.model.PhotoInfo
import com.ajitesh.imagesearch.scheduler.ISchedulerProvider
import com.ajitesh.imagesearch.util.Util

class MainViewModel(
    app: Application,
    private val dataLayer: IDataLayer,
    private val schedulerProvider: ISchedulerProvider
) : AndroidViewModel(app) {

    private val searchResults =
        MutableLiveData<MutableList<PhotoInfo>>().apply { value = mutableListOf() }
    private val isLoading = MutableLiveData<Boolean>().apply { value = false }
    private val currPageNumber = MutableLiveData<Int>().apply { value = 0 }
    private val toastMessage = SingleLiveEvent<String>()

    @VisibleForTesting
    var searchedText: String = ""

    val currSearchText = MutableLiveData<String>().apply { value = "" }

    fun getSearchResults(): LiveData<MutableList<PhotoInfo>> = searchResults
    fun isLoading(): LiveData<Boolean> = isLoading
    fun getToastMessage(): LiveData<String> = toastMessage

    fun searchImage() {

        if (currSearchText.value.isNullOrBlank()) {
            toastMessage.postValue("Empty Search String")
            return
        }

        currPageNumber.value = 0
        searchResults.value = mutableListOf()

        searchedText = currSearchText.value!!

        loadNextPage()
    }

    private fun loadNextPage() {

        if (isLoading.value!!) {
            return
        }

        isLoading.value = true

        dataLayer.fetchImageInfo(
            searchText = searchedText,
            pageNumber = currPageNumber.value!! + 1
        )
            .subscribeOn(schedulerProvider.io)
            .map {
                it.photos.photo.forEach { photoInfo ->
                    Util.setPhotoUrl(photoInfo)
                }
                it
            }
            .observeOn(schedulerProvider.mainThread)
            .subscribe({
                isLoading.value = false
                currPageNumber.value = it.photos.page
                searchResults.value!!.addAll(it.photos.photo)

                if (searchResults.value!!.isEmpty()) {
                    toastMessage.postValue("No results found")
                }
            }, {
                isLoading.value = false
                toastMessage.postValue("Oops!, Error occurred.")
            })
    }

    fun checkIfNeedToLoadMore(lastVisiblePos: Int) {

        if (lastVisiblePos == searchResults.value!!.size - 1) {
            loadNextPage()
        }
    }
}