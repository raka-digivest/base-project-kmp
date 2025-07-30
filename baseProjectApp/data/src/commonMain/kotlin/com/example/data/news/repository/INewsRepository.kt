package com.example.data.news.repository

import com.example.data.news.model.News


interface INewsRepository {
    suspend fun getNews(): List<News>
}