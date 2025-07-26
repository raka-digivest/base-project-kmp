package com.example.data.repository.news

import com.example.data.model.news.News

interface INewsRepository {
    suspend fun getNews(): List<News>
}