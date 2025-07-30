package com.example.data.news.api

import com.example.data.news.model.News

interface INewsApi {
    suspend fun getNews(): List<News>
}