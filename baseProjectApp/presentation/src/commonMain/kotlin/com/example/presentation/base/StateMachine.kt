package com.example.presentation.base

import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class StateMachine<State : Any, Event, Effect> : BaseViewModel() {
    private val initial by lazy { getInitialState() }
    abstract fun getInitialState(): State

    val state by lazy { createState<State>(initial) }

    private val event = MutableSharedFlow<Event>()
    val effect = MutableSharedFlow<Effect>()

    private val mapState = HashMap<String, Any>()

    init {
        screenModelScope.launch {
            event.collect { evt ->
                Logger.d("STATE_MACHINE event = $evt")
                mapEvent(evt, state.value)
            }
        }
    }

    fun sendEvent(newEvent: Event) {
        screenModelScope.launch {
            event.emit(newEvent)
        }
    }

    protected fun sendEffect(newEffect: Effect) {
        screenModelScope.launch {
            effect.emit(newEffect)
        }
    }

    fun resetState() {
        state.setValue(getInitialState())
    }

    fun <T : Any> saveState(key: String, state: T) {
        mapState[key] = state
    }

    fun <T : Any> getState(key: String): T? {
        return mapState[key] as? T
    }

    protected abstract fun mapEvent(event: Event, lastState: State)
}