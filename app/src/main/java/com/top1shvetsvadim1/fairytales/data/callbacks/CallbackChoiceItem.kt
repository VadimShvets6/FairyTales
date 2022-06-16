package com.top1shvetsvadim1.fairytales.data.callbacks

import com.top1shvetsvadim1.fairytales.domain.choiceDomain.ChoiceItem

interface CallbackChoiceItem {

    fun onCallback(value : List<ChoiceItem>)
}