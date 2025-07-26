package com.example.presentation.base

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel : ScreenModel {

    protected fun <T> createState(initialValue: T): StateFlow<T> = MutableStateFlow(initialValue)
    fun <T> StateFlow<T>.setValue(state: T) {
        if (this is MutableStateFlow) {
            Logger.d("STATE_MACHINE : state = $state")
            this.value = state
        }
    }

    protected fun <T> StateFlow<T>.updateState(function: (T) -> T) {
        if (this is MutableStateFlow) {
            this.update(function)
        }
    }

    protected fun <T> createSharedFlow(): MutableSharedFlow<T> = MutableSharedFlow<T>().apply {
        shareIn(screenModelScope, SharingStarted.WhileSubscribed())
    }

    fun <T> SharedFlow<T>.setValue(data: T) {
        if (this is MutableSharedFlow) {
            screenModelScope.launch {
                Logger.d("STATE_MACHINE effect = $data")
                emit(data)
            }
        }
    }
}