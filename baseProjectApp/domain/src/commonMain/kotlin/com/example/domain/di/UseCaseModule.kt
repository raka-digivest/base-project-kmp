package com.example.domain.di

import com.example.domain.news.di.newsUseCaseModule
import org.koin.core.module.Module

val useCaseModule: List<Module>
    get() = listOf(
        newsUseCaseModule
    )