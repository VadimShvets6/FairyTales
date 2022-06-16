package com.top1shvetsvadim1.fairytales.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import com.top1shvetsvadim1.fairytales.databinding.FragmentCatalogBinding
import com.top1shvetsvadim1.fairytales.databinding.ItemCatalogBinding
import com.top1shvetsvadim1.fairytales.domain.ChoiceItem
import com.top1shvetsvadim1.fairytales.presentation.adapters.callbacks.CatalogDiffCallback
import com.top1shvetsvadim1.fairytales.presentation.adapters.viewHolders.CatalogViewHolder

class CatalogAdapter : ListAdapter<ChoiceItem, CatalogViewHolder>(CatalogDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val binding = ItemCatalogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val choiceItem = getItem(position)
        with(holder.binding){
            with(choiceItem){
                tvType.text = choiceItem.name
                Picasso.get().load(imageUrl).into(ivLogo)
            }
        }
    }
}