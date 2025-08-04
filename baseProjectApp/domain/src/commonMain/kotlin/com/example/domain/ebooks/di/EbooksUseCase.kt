package com.example.domain.ebooks.di

import com.example.domain.ebooks.GetEbooksUseCase
import org.koin.dsl.module

val ebooksUseCaseModule = module {
    factory { GetEbooksUseCase(repository = get()) }
}