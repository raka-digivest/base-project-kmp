package com.example.baseprocject.di

import com.example.data.di.dataModule
import com.example.data.di.ktorModule
import com.example.domain.di.useCaseModule
import com.example.presentation.di.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        ktorModule,
        *dataModule.toTypedArray(),
        *useCaseModule.toTypedArray() ,
        *viewModelModule.toTypedArray(),
    )
}