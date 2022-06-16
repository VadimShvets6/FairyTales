package com.top1shvetsvadim1.fairytales.data.repositorys

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.top1shvetsvadim1.fairytales.data.callbacks.CallbackDetailList
import com.top1shvetsvadim1.fairytales.domain.choiceDomain.ChoiceItem
import com.top1shvetsvadim1.fairytales.domain.detailDomain.DetailItem
import com.top1shvetsvadim1.fairytales.domain.detailDomain.DetailRepository

object DetailRepositoryImpl : DetailRepository {

    private val list = arrayListOf<DetailItem>()

    private const val COLLECTION_NAME = "fairyTales"
    private const val KEY_NAME = "name"
    private const val KEY_DESCRIPTION = "description"
    private const val KEY_AUTHOR = "author"
    private const val KEY_GENRE = "genre"
    private const val KEY_IMAGE_URL = "imageUrl"
    private const val KEY_MEDIA_URL = "mediaUrl"
    private const val KEY_TEXT = "text"


    override suspend fun loadDetail(callbackDetailList: CallbackDetailList) {
        val db = Firebase.firestore
        db.collection(COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    list.add(
                        DetailItem(
                            document.data[KEY_NAME].toString(),
                            document.data[KEY_DESCRIPTION].toString(),
                            document.data[KEY_AUTHOR].toString(),
                            document.data[KEY_GENRE].toString(),
                            document.data[KEY_IMAGE_URL].toString(),
                            document.data[KEY_MEDIA_URL].toString(),
                            document.data[KEY_TEXT].toString()
                        )
                    )
                    Log.d("MainActivity", list.toString())
                }
                callbackDetailList.onCallback(list)
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.", exception)
            }
    }
}