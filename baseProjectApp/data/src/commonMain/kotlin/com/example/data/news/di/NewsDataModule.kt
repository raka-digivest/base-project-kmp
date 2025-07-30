package com.example.data.news.di

import com.example.data.news.api.INewsApi
import com.example.data.news.api.NewsApi
import com.example.data.news.repository.INewsRepository
import com.example.data.news.repository.NewsRepositoryImpl
import org.koin.dsl.module

val newsDataModule = module {
    single<INewsApi> { NewsApi(httpClient = get()) }
    single<INewsRepository> { NewsRepositoryImpl(api = get()) }
}