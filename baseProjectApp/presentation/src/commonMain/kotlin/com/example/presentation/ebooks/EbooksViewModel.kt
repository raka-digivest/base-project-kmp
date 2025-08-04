package com.example.presentation.ebooks

import cafe.adriel.voyager.core.model.screenModelScope
import com.example.domain.ebooks.GetEbooksUseCase
import com.example.presentation.base.StateMachine
import kotlinx.coroutines.launch

class EbooksViewModel(private val getEbooksUseCase: GetEbooksUseCase) :
    StateMachine<EbooksViewState, EbooksViewEvent, EbooksViewEffect>() {

    override fun getInitialState(): EbooksViewState = EbooksViewState.Initial

    override fun mapEvent(event: EbooksViewEvent, lastState: EbooksViewState) {
        when (lastState) {
            EbooksViewState.Initial -> {
                when (event) {
                    EbooksViewEvent.LoadEbooks -> getEbooks()
                }
            }
            else -> {}
        }
    }

    private fun getEbooks() {
        screenModelScope.launch {
            runCatching {
                getEbooksUseCase()
            }.onSuccess {
                state.setValue(EbooksViewState.Loaded(it))
            }.onFailure {
                state.setValue(EbooksViewState.Error(it.message.toString(), state.value.ebooks))
            }
        }
    }

}