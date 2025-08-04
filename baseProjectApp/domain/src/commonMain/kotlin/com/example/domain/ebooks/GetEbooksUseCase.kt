package com.example.domain.ebooks

import com.example.data.ebooks.repository.IEbooksRepository

class GetEbooksUseCase(private val repository: IEbooksRepository) {
    suspend operator fun invoke() = repository.getEbooks()
}