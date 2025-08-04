package com.example.presentation.ebooks.di

import com.example.presentation.ebooks.EbooksViewModel
import org.koin.dsl.module

val ebooksViewModelModule = module {
    factory { EbooksViewModel(getEbooksUseCase = get()) }
}
