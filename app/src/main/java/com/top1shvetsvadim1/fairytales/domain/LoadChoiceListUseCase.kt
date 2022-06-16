package com.top1shvetsvadim1.fairytales.domain

import com.top1shvetsvadim1.fairytales.data.MyCallback

class LoadChoiceListUseCase(
    private val repository: ChoiceRepository
) {
    suspend operator fun invoke(myCallbck : MyCallback) = repository.loadChoice(myCallbck)
}