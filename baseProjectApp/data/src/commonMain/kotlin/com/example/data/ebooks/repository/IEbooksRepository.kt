package com.example.data.ebooks.repository

import com.example.data.ebooks.model.Ebook


interface IEbooksRepository {
    suspend fun getEbooks(): List<Ebook>
}