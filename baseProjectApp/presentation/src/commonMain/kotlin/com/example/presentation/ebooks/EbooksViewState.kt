package com.example.presentation.ebooks

import com.example.data.ebooks.model.Ebook


sealed interface EbooksViewState {
    val ebooks: List<Ebook>

    data object Initial : EbooksViewState {
        override val ebooks: List<Ebook> = listOf()
    }

    data class Loaded(override val ebooks: List<Ebook>) : EbooksViewState

    data class Error(val message: String, override val ebooks: List<Ebook>): EbooksViewState
}

sealed interface EbooksViewEvent {
    data object LoadEbooks: EbooksViewEvent
}

sealed interface EbooksViewEffect {
    data class ShowToast(val message: String): EbooksViewEffect
}