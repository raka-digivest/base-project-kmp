package com.example.data.news.repository

import com.example.data.news.api.INewsApi
import com.example.data.news.model.News

class NewsRepositoryImpl(private val api: INewsApi) : INewsRepository {

    override suspend fun getNews(): List<News> = api.getNews()

}