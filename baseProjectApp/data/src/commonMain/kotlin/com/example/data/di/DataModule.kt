package com.example.data.di

import com.example.data.ebooks.di.ebooksDataModule
import com.example.data.news.di.newsDataModule
import org.koin.core.module.Module

val dataModule: List<Module>
    get() = listOf(
        newsDataModule,
        ebooksDataModule
    )