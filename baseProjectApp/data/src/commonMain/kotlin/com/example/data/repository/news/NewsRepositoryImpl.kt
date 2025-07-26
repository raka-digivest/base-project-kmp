package com.example.data.repository.news

import com.example.data.api.news.INewsApi
import com.example.data.model.news.News

class NewsRepositoryImpl(private val api: INewsApi) : INewsRepository {

    override suspend fun getNews(): List<News> = api.getNews()

}