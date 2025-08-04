package com.example.data.ebooks.repository

import com.example.data.ebooks.api.IEbooksApi
import com.example.data.ebooks.model.Ebook

class EbooksRepositoryImpl(private val api: IEbooksApi) : IEbooksRepository {
    override suspend fun getEbooks(): List<Ebook> = api.getEbooks()
}