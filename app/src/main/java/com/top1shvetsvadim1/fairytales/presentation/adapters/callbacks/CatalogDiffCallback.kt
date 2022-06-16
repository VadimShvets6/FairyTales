package com.top1shvetsvadim1.fairytales.presentation.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.top1shvetsvadim1.fairytales.domain.ChoiceItem

object CatalogDiffCallback : DiffUtil.ItemCallback<ChoiceItem>() {
    override fun areItemsTheSame(oldItem: ChoiceItem, newItem: ChoiceItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ChoiceItem, newItem: ChoiceItem): Boolean {
        return oldItem == newItem
    }
}