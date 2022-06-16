package com.top1shvetsvadim1.fairytales.data

import com.top1shvetsvadim1.fairytales.domain.ChoiceItem

interface MyCallback {

    fun onCallback(value : List<ChoiceItem>)
}