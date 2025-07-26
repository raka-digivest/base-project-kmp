package com.example.presentation.di

import com.example.presentation.news.NewsViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { NewsViewModel(getNewsUseCase = get()) }
}