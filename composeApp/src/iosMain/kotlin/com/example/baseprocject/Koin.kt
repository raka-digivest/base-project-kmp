package com.example.baseprocject

import com.example.data.di.dataModule
import com.example.data.di.ktorModule
import com.example.domain.di.useCaseModule
import com.example.presentation.di.viewModelModule
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        ktorModule,
        *dataModule.toTypedArray(),
        *useCaseModule.toTypedArray() ,
        *viewModelModule.toTypedArray(),
    )
}