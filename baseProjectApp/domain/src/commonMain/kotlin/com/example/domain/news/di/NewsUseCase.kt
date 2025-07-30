package com.example.domain.news.di

import com.example.domain.news.GetNewsUseCase
import org.koin.dsl.module

val newsUseCaseModule = module {
    factory { GetNewsUseCase(repository = get()) }
}