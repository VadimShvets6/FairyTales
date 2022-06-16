package com.top1shvetsvadim1.fairytales.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.top1shvetsvadim1.fairytales.data.repositorys.ChoiceRepositoryImpl
import com.top1shvetsvadim1.fairytales.data.callbacks.CallbackChoiceItem
import com.top1shvetsvadim1.fairytales.data.callbacks.CallbackDetailList
import com.top1shvetsvadim1.fairytales.data.repositorys.DetailRepositoryImpl
import com.top1shvetsvadim1.fairytales.domain.choiceDomain.ChoiceItem
import com.top1shvetsvadim1.fairytales.domain.choiceDomain.LoadChoiceListUseCase
import com.top1shvetsvadim1.fairytales.domain.detailDomain.DetailItem
import com.top1shvetsvadim1.fairytales.domain.detailDomain.LoadDetailListUseCase
import com.top1shvetsvadim1.fairytales.presentation.state.Loading
import com.top1shvetsvadim1.fairytales.presentation.state.State
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {

    private val _choiceList = MutableLiveData<List<ChoiceItem>>()
    val choiceList: LiveData<List<ChoiceItem>>
        get() = _choiceList

    private val _audioBookList = MutableLiveData<List<DetailItem>>()
    val audioBookList: LiveData<List<DetailItem>>
        get() = _audioBookList

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val repository = ChoiceRepositoryImpl
    private val repositoryDetail = DetailRepositoryImpl
    private val loadChoiceListUseCase = LoadChoiceListUseCase(repository)
    private val loadLoadDetailListUseCase = LoadDetailListUseCase(repositoryDetail)

    fun getChoiceList() {
        _state.value = Loading(true)
        viewModelScope.launch {
            loadChoiceListUseCase(object : CallbackChoiceItem {
                override fun onCallback(value: List<ChoiceItem>) {
                    _choiceList.value = value
                    _state.value = Loading(false)
                }
            })
        }
    }

    fun getAudioList() {
        _state.value = Loading(true)
        viewModelScope.launch {
            loadLoadDetailListUseCase(object : CallbackDetailList {
                override fun onCallback(value: List<DetailItem>) {
                    _audioBookList.value = value
                    _state.value = Loading(false)
                }
            })
        }
    }

}