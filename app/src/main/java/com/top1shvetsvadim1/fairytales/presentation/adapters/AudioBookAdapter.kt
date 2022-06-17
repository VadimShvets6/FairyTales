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


    //Создаем значение к которому можно присвоеть значение из активити слушателя
    var onAudioBookClickListener: OnAudioBookClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioBookViewHolder {
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

                root.setOnClickListener {
                    onAudioBookClickListener?.onItemClick(this)
                }
            }
        }
    }

    //Добавим интерфейс слушатель клика
    interface OnAudioBookClickListener {
        fun onItemClick(detail: DetailItem)
    }

}