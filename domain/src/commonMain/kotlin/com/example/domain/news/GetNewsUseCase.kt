package com.example.domain.news

import com.example.data.repository.news.INewsRepository

class GetNewsUseCase(private val repository: INewsRepository) {

    suspend operator fun invoke() = repository.getNews()
}