package com.ajitesh.imagesearch.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ajitesh.imagesearch.adapter.ImageSearchResultAdapter
import com.ajitesh.imagesearch.model.PhotoInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


sealed class Util {

    companion object {
        @JvmStatic
        @BindingAdapter("img_src")
        fun setImageSrcLogo(imageView: ImageView, url: String?) {
            Glide.with(imageView)
                .load(url)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .apply(RequestOptions.noAnimation())
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("photoList")
        fun setImageSearchResultAdapter(recyclerView: RecyclerView, photoList: List<PhotoInfo>?) {

            val list = photoList ?: mutableListOf()
            var adapter = recyclerView.adapter

            if (adapter == null || adapter !is ImageSearchResultAdapter) {
                recyclerView.adapter =
                    ImageSearchResultAdapter(list)
            } else {
                adapter.updateSearchResult(list)
            }
        }

        @JvmStatic
        fun setPhotoUrl(photoInfo: PhotoInfo) {
            val url =
                "https://farm${photoInfo.farm}.staticflickr.com/${photoInfo.server}/${photoInfo.id}_${photoInfo.secret}_m.jpg"
            photoInfo.url = url
        }

        @JvmStatic
        fun hideKeyboard(activity: Activity) {

            val imm: InputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view: View? = activity.getCurrentFocus()
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}