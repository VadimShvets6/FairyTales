package com.top1shvetsvadim1.fairytales.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.top1shvetsvadim1.fairytales.data.ChoiceRepositoryImpl
import com.top1shvetsvadim1.fairytales.data.MyCallback
import com.top1shvetsvadim1.fairytales.domain.ChoiceItem
import com.top1shvetsvadim1.fairytales.domain.LoadChoiceListUseCase
import com.top1shvetsvadim1.fairytales.presentation.state.Loading
import com.top1shvetsvadim1.fairytales.presentation.state.State
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {

    private val _choiceList = MutableLiveData<List<ChoiceItem>>()
    val choiceList: LiveData<List<ChoiceItem>>
        get() = _choiceList

    private val _state = MutableLiveData<State>()
    val state : LiveData<State>
    get() = _state

    private val repository = ChoiceRepositoryImpl
    private val loadChoiceListUseCase = LoadChoiceListUseCase(repository)

    fun getChoiceList() {
        _state.value = Loading(true)
        viewModelScope.launch {
            loadChoiceListUseCase(object : MyCallback {
                override fun onCallback(value: List<ChoiceItem>) {
                    _choiceList.value = value
                    _state.value = Loading(false)
                }
            })
        }
    }
}