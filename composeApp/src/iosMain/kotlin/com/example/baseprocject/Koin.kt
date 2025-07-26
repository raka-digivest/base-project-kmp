package com.example.baseprocject

import com.example.data.di.ktorModule
import com.example.data.di.repositoryModule
import com.example.domain.di.useCaseModule
import com.example.presentation.di.viewModelModule
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        ktorModule,
        repositoryModule,
        *useCaseModule.toTypedArray() ,
        viewModelModule,
    )
}