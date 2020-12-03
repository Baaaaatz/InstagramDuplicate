package com.batzalcancia.dashboard.presentation.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.batzalcancia.core.utils.ViewBindingViewHolder
import com.batzalcancia.dashboard.R
import com.batzalcancia.dashboard.databinding.ItemPictureBinding
import com.batzalcancia.core.domain.local.entities.InstagramPicture


class PicturesAdapter(
    private val setupPictureBinding: (View) -> ItemPictureBinding
) : PagingDataAdapter<InstagramPicture, ViewBindingViewHolder<ItemPictureBinding>>(
    object : DiffUtil.ItemCallback<InstagramPicture>() {
        override fun areItemsTheSame(oldItem: InstagramPicture, newItem: InstagramPicture) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: InstagramPicture, newItem: InstagramPicture) =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<ItemPictureBinding> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return ViewBindingViewHolder(setupPictureBinding(view))
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<ItemPictureBinding>,
        position: Int
    ) {
        val picture = getItem(position)
        holder.viewBinding.run {
            txtDesc.text = picture?.caption

            imgPost.load(picture?.picture?.let { byteArrayToBitmap(it) })
        }
    }

    private fun byteArrayToBitmap(byteArray: ByteArray) =
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)


}