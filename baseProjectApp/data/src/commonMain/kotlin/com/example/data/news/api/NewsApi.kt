package com.example.data.news.api

import com.example.data.helper.safeRequest
import com.example.data.news.model.News
import com.example.data.news.model.remote.NewsResponse
import io.ktor.client.HttpClient

class NewsApi(private val httpClient: HttpClient): INewsApi {

    override suspend fun getNews(): List<News> {
        return runCatching {
            safeRequest<List<NewsResponse>>(
                client = httpClient,
                url = "v2/everything?q=tesla&from=2025-06-26&sortBy=publishedAt&apiKey=b4858baaad5b4b41b339134e9e37a7b0",
            ).map { News(it.title.orEmpty()) }
        }.getOrElse { exception ->
            //Logger.i { "Error during process: ${exception.message}" }
            throw exception
        }
    }

}