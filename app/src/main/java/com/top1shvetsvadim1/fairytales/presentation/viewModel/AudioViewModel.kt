package com.top1shvetsvadim1.fairytales.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AudioViewModel : ViewModel() {

    private val _timeAudio = MutableLiveData<String>()
    val timerAudio: LiveData<String>
        get() = _timeAudio

    private val _maxTimerAudio = MutableLiveData<String>()
    val maxTimerAudio: LiveData<String>
        get() = _maxTimerAudio

    fun secondsToMinute(milliseconds: Int) {
        val minutes: Int = milliseconds / 1000 / 60
        val seconds: Int = milliseconds / 1000 % 60
        _timeAudio.value = String.format("%1d:%02d", minutes, seconds)
    }

    fun maxTimeAudio(milliseconds: Int){
        val minutes: Int = milliseconds / 1000 / 60
        val seconds: Int = milliseconds / 1000 % 60
        _maxTimerAudio.value = String.format("%1d:%02d", minutes, seconds)
    }
}