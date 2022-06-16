package com.top1shvetsvadim1.fairytales.presentation.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.top1shvetsvadim1.fairytales.domain.detailDomain.DetailItem

object AudioBookDiffCallback : DiffUtil.ItemCallback<DetailItem>() {
    override fun areItemsTheSame(oldItem: DetailItem, newItem: DetailItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: DetailItem, newItem: DetailItem): Boolean {
        return oldItem == newItem
    }
}