package com.top1shvetsvadim1.fairytales.domain.detailDomain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailItem(
    val name : String = "",
    val description : String = "",
    val author : String = "",
    val genre : String = "",
    val imageUrl : String = "",
    val mediaUrl : String = "",
    val text : String = ""
) : Parcelable
