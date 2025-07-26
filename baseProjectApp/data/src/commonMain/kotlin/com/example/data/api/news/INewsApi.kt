package com.example.data.api.news

import com.example.data.model.news.News

interface INewsApi {
    suspend fun getNews(): List<News>
}