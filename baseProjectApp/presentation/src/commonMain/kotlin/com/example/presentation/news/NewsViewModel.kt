package com.example.presentation.news

import cafe.adriel.voyager.core.model.screenModelScope
import com.example.domain.news.GetNewsUseCase
import com.example.presentation.base.StateMachine
import kotlinx.coroutines.launch

class NewsViewModel(private val getNewsUseCase: GetNewsUseCase) :
    StateMachine<NewsViewState, NewsViewEvent, NewsViewEffect>() {

    override fun getInitialState(): NewsViewState = NewsViewState.Initial

    override fun mapEvent(event: NewsViewEvent, lastState: NewsViewState) {
        when (lastState) {
            NewsViewState.Initial -> {
                when (event) {
                    NewsViewEvent.LoadNews -> getNews()
                }
            }
            else -> {}
        }
    }

    private fun getNews() {
        screenModelScope.launch {
            runCatching {
                getNewsUseCase()
            }.onSuccess {
                state.setValue(NewsViewState.Loaded(it))
            }.onFailure {
                state.setValue(NewsViewState.Error(it.message.toString(), state.value.news))
            }
        }
    }

}