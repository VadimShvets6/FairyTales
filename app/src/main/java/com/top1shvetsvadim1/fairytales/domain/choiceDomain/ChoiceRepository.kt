package com.top1shvetsvadim1.fairytales.domain.choiceDomain

import com.top1shvetsvadim1.fairytales.data.callbacks.CallbackChoiceItem

interface ChoiceRepository {

    suspend fun loadChoice(callbackChoiceItem : CallbackChoiceItem)
}