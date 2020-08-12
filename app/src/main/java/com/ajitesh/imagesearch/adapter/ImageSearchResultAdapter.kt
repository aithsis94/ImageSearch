package com.ajitesh.imagesearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajitesh.imagesearch.databinding.ListItemImageSearchResultBinding
import com.ajitesh.imagesearch.model.PhotoInfo
import com.ajitesh.imagesearch.viewholder.ImageItemViewHolder

class ImageSearchResultAdapter(private var searchResults: List<PhotoInfo>) :
    RecyclerView.Adapter<ImageItemViewHolder>() {


    fun updateSearchResult(searchResults: List<PhotoInfo>) {
        this.searchResults = searchResults
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = ListItemImageSearchResultBinding.inflate(inflater, parent, false)
        return ImageItemViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        holder.bind(searchResults[position])
    }
}