package com.top1shvetsvadim1.fairytales.domain.choiceDomain

import com.top1shvetsvadim1.fairytales.data.callbacks.CallbackChoiceItem

class LoadChoiceListUseCase(
    private val repository: ChoiceRepository
) {
    suspend operator fun invoke(callbckChoiceItem : CallbackChoiceItem) = repository.loadChoice(callbckChoiceItem)
}