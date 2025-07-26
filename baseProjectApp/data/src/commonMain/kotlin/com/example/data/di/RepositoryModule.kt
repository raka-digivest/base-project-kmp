package com.example.data.di

import com.example.data.api.news.INewsApi
import com.example.data.api.news.NewsApi
import com.example.data.repository.news.INewsRepository
import com.example.data.repository.news.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<INewsApi> { NewsApi(httpClient = get()) }
    single<INewsRepository> { NewsRepositoryImpl(api = get()) }
}