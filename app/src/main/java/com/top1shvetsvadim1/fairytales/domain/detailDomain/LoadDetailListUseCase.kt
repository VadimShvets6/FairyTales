package com.top1shvetsvadim1.fairytales.domain.detailDomain

import com.top1shvetsvadim1.fairytales.data.callbacks.CallbackDetailList

class LoadDetailListUseCase(
    private val repository: DetailRepository
) {
    suspend operator fun invoke(callbackDetailList : CallbackDetailList) = repository.loadDetail(callbackDetailList)
}