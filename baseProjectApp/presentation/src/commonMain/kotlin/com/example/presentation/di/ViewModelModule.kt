package com.example.presentation.di

import com.example.presentation.news.di.newViewModelModule
import org.koin.core.module.Module


val viewModelModule: List<Module>
    get() = listOf(
        newViewModelModule
    )