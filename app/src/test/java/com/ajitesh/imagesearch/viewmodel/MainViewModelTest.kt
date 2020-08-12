package com.ajitesh.imagesearch.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ajitesh.imagesearch.TestSchedulerProviderImpl
import com.ajitesh.imagesearch.data.IDataLayer
import com.ajitesh.imagesearch.model.ImageSearchResponse
import com.ajitesh.imagesearch.model.PhotoInfo
import com.ajitesh.imagesearch.model.PhotoPageInfo
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.Exception


class MainViewModelTest {

    var rule: TestRule = InstantTaskExecutorRule()
        @Rule get


    private lateinit var app: Application
    private lateinit var datalayer: IDataLayer

    private lateinit var mainViewModel: MainViewModel

    private lateinit var schedulerProvider: TestSchedulerProviderImpl

    @Before
    fun setup() {
        this.app = mock(Application::class.java)
        this.datalayer = mock(IDataLayer::class.java)
        this.schedulerProvider = TestSchedulerProviderImpl()
        this.mainViewModel = MainViewModel(this.app, this.datalayer, schedulerProvider)
    }

    @Test
    fun `should fetch next page when user scrolls to bottom`() {

        mainViewModel.searchedText = "abc"
        val searchResults = mainViewModel.getSearchResults().value!!

        searchResults.add(mock(PhotoInfo::class.java))
        searchResults.add(mock(PhotoInfo::class.java))

        val photoList = generateMockImageSearchResponse()

        `when`(this.datalayer.fetchImageInfo("abc", 1)).thenReturn(Single.just(photoList))

        mainViewModel.checkIfNeedToLoadMore(1)

        Assert.assertTrue(mainViewModel.getSearchResults().value!!.size == 4)
    }

    @Test
    fun `should fetch data when user searchImage method is called`() {

        mainViewModel.currSearchText.value = "abc"

        val photoList = generateMockImageSearchResponse()

        `when`(this.datalayer.fetchImageInfo("abc", 1)).thenReturn(Single.just(photoList))

        mainViewModel.searchImage()

        Assert.assertTrue(mainViewModel.getSearchResults().value!!.size == 2)
    }

    @Test
    fun `should call no result found toast message data when searchImage method is called`() {

        mainViewModel.currSearchText.value = "abc"

        val photoList = generateMockImageSearchResponse(0)

        `when`(this.datalayer.fetchImageInfo("abc", 1)).thenReturn(Single.just(photoList))

        mainViewModel.searchImage()

        val toastString = mainViewModel.getToastMessage().value

        Assert.assertEquals("No results found", toastString)
    }

    @Test
    fun `should call error occurred toast message data when searchImage method throws error`() {

        mainViewModel.currSearchText.value = "abc"
        val searchResults = mainViewModel.getSearchResults().value!!

        `when`(this.datalayer.fetchImageInfo("abc", 1)).thenReturn(Single.error(Exception()))

        mainViewModel.searchImage()

        val toastString = mainViewModel.getToastMessage().value

        Assert.assertEquals("Oops!, Error occurred.", toastString)
    }

    @Test
    fun `should call empty string toast message data when searchImage method is called with empty string`() {

        mainViewModel.currSearchText.value = ""

        val photoList = generateMockImageSearchResponse(0)

        `when`(this.datalayer.fetchImageInfo("", 1)).thenReturn(Single.just(photoList))

        mainViewModel.searchImage()

        val toastString = mainViewModel.getToastMessage().value

        Assert.assertEquals("Empty Search String", toastString)
    }

    @Test
    fun `should properly set the photo url when data is fetched`() {

        mainViewModel.currSearchText.value = "abc"

        val photoList = generateMockImageSearchResponse(1)

        `when`(this.datalayer.fetchImageInfo("abc", 1)).thenReturn(Single.just(photoList))

        mainViewModel.searchImage()

        val items = mainViewModel.getSearchResults().value!!
        Assert.assertEquals("https://farmfarm.staticflickr.com/server/id_secret_m.jpg", items[0].url)
    }


    private fun generateMockImageSearchResponse(photoCount: Int = 2): ImageSearchResponse {

        val photoPageInfo = PhotoPageInfo(
            photoCount, 4, 2, (0 until photoCount).toList().map {
                generateMockPhotoInfo()
            }, 2
        )

        return ImageSearchResponse("ok", photoPageInfo)
    }

    private fun generateMockPhotoInfo(): PhotoInfo {
        return PhotoInfo("", "server", "", "", "farm", "id", "secret", "title", "")
    }

}

