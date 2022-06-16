package com.top1shvetsvadim1.fairytales.domain

import androidx.lifecycle.LiveData
import com.top1shvetsvadim1.fairytales.data.MyCallback

interface ChoiceRepository {

    suspend fun loadChoice(myCallbck : MyCallback)
}