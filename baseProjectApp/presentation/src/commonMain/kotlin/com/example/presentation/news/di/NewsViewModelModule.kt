package com.example.presentation.news.di

import com.example.presentation.news.NewsViewModel
import org.koin.dsl.module

val newViewModelModule = module {
    factory { NewsViewModel(getNewsUseCase = get()) }
}
