package com.example.data.ebooks.api

import com.example.data.ebooks.model.Ebook

interface IEbooksApi {
    suspend fun getEbooks(): List<Ebook>
}