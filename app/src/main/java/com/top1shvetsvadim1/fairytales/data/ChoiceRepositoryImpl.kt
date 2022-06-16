package com.top1shvetsvadim1.fairytales.data

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.top1shvetsvadim1.fairytales.domain.ChoiceItem
import com.top1shvetsvadim1.fairytales.domain.ChoiceRepository
import kotlinx.coroutines.*

object ChoiceRepositoryImpl : ChoiceRepository {

    private val list = arrayListOf<ChoiceItem>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    private var job: Deferred<ArrayList<ChoiceItem>>? = null

    override suspend fun loadChoice(myCallbck : MyCallback) {
        val db = Firebase.firestore
        db.collection("choice")
            .get()
            .addOnSuccessListener { result ->
                    for (document in result) {
                        list.add(
                            ChoiceItem(
                                document.data["name"].toString(),
                                document.data["imageUrl"].toString(),
                            )
                        )
                        Log.d("MainActivity", list.toString())
                }
                myCallbck.onCallback(list)
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.", exception)
            }
    }
}