package com.top1shvetsvadim1.fairytales.data.repositorys

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.top1shvetsvadim1.fairytales.data.callbacks.CallbackChoiceItem
import com.top1shvetsvadim1.fairytales.domain.choiceDomain.ChoiceItem
import com.top1shvetsvadim1.fairytales.domain.choiceDomain.ChoiceRepository
import kotlinx.coroutines.*

object ChoiceRepositoryImpl : ChoiceRepository {

    private val list = arrayListOf<ChoiceItem>()

    override suspend fun loadChoice(callbackChoiceItem : CallbackChoiceItem) {
        val db = Firebase.firestore
        db.collection("choice")
            .get()
            .addOnSuccessListener { result ->
                    for (document in result) {
                        list.add(
                            ChoiceItem(
                                document.data["name"].toString(),
                                document.data["imageUrl"].toString()
                            )
                        )
                        Log.d("MainActivity", list.toString())
                }
                callbackChoiceItem.onCallback(list)
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.", exception)
            }
    }
}