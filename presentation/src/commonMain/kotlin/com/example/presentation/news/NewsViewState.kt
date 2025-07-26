package com.example.presentation.news

import com.example.data.model.news.News

sealed interface NewsViewState {
    val news: List<News>

    data object Initial : NewsViewState {
        override val news: List<News> = listOf()
    }

    data class Loaded(override val news: List<News>) : NewsViewState

    data class Error(val message: String, override val news: List<News>): NewsViewState
}

sealed interface NewsViewEvent {
    data object LoadNews: NewsViewEvent
}

sealed interface NewsViewEffect {
    data class ShowToast(val message: String): NewsViewEffect
}