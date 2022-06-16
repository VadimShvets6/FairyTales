package com.top1shvetsvadim1.fairytales.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import com.top1shvetsvadim1.fairytales.R
import com.top1shvetsvadim1.fairytales.databinding.ItemAudiobookBinding
import com.top1shvetsvadim1.fairytales.databinding.ItemCatalogBinding
import com.top1shvetsvadim1.fairytales.domain.detailDomain.DetailItem
import com.top1shvetsvadim1.fairytales.presentation.adapters.callbacks.AudioBookDiffCallback
import com.top1shvetsvadim1.fairytales.presentation.adapters.viewHolders.AudioBookViewHolder
import com.top1shvetsvadim1.fairytales.presentation.adapters.viewHolders.CatalogViewHolder

class AudioBookAdapter : ListAdapter<DetailItem, AudioBookViewHolder>(AudioBookDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioBookViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ITEM -> R.layout.item_audiobook
            VIEW_TYPE_MORE -> R.layout.item_more_book
            else -> throw RuntimeException("Unknow viewType: $viewType")
        }
        val binding = ItemAudiobookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AudioBookViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AudioBookViewHolder, position: Int) {
        val audioItem = getItem(position)
        with(holder.binding){
            with(audioItem){
                tvName.text = audioItem.name
                tvAuthor.text = audioItem.author
                Picasso.get().load(imageUrl).into(ivLogo)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            VIEW_TYPE_MORE
        } else {
            VIEW_TYPE_ITEM
        }
    }

    companion object {
        const val VIEW_TYPE_MORE = 0
        const val VIEW_TYPE_ITEM = 1
    }
}