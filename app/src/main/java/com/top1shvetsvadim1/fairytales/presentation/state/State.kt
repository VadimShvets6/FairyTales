package com.top1shvetsvadim1.fairytales.presentation.state

sealed class State

class Loading(val isLoad : Boolean = false) : State()