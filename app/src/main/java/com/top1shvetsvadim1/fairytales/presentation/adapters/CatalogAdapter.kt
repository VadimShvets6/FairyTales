package com.top1shvetsvadim1.fairytales.presentation.adapters

import android.content.Context
import android.graphics.BlurMaskFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.top1shvetsvadim1.fairytales.databinding.ItemCatalogBinding
import com.top1shvetsvadim1.fairytales.domain.choiceDomain.ChoiceItem
import com.top1shvetsvadim1.fairytales.presentation.adapters.callbacks.CatalogDiffCallback
import com.top1shvetsvadim1.fairytales.presentation.adapters.viewHolders.CatalogViewHolder
import jp.wasabeef.picasso.transformations.BlurTransformation

class CatalogAdapter(val context : Context) : ListAdapter<ChoiceItem, CatalogViewHolder>(CatalogDiffCallback) {

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