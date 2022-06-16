package com.top1shvetsvadim1.fairytales.domain.detailDomain

import com.top1shvetsvadim1.fairytales.data.callbacks.CallbackDetailList

interface DetailRepository {

    suspend fun loadDetail(callbackDetailList: CallbackDetailList)

}