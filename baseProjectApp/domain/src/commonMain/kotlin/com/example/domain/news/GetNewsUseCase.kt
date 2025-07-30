package com.example.domain.news

import com.example.data.news.repository.INewsRepository

class GetNewsUseCase(private val repository: INewsRepository) {

    suspend operator fun invoke() = repository.getNews()
}