package com.top1shvetsvadim1.fairytales.data.callbacks

import com.top1shvetsvadim1.fairytales.domain.detailDomain.DetailItem

interface CallbackDetailList {

    fun onCallback(value : List<DetailItem>)
}