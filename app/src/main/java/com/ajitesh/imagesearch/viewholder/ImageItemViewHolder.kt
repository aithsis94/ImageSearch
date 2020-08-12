package com.ajitesh.imagesearch.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ajitesh.imagesearch.BR
import com.ajitesh.imagesearch.model.PhotoInfo

class ImageItemViewHolder(private val viewBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(photoInfo: PhotoInfo) {
        viewBinding.setVariable(BR.photoInfo, photoInfo)
        viewBinding.executePendingBindings()
    }
}