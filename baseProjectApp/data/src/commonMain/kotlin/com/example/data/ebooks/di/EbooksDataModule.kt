package com.example.data.ebooks.di

import com.example.data.ebooks.api.EbooksApi
import com.example.data.ebooks.api.IEbooksApi
import com.example.data.ebooks.repository.EbooksRepositoryImpl
import com.example.data.ebooks.repository.IEbooksRepository
import org.koin.dsl.module

val ebooksDataModule = module {
    single<IEbooksApi> { EbooksApi(httpClient = get()) }
    single<IEbooksRepository> { EbooksRepositoryImpl(api = get()) }
}