package com.example.data.ebooks.api

import com.example.data.ebooks.model.Ebook
import com.example.data.ebooks.model.remote.EbooksResponse
import com.example.data.helper.safeRequest
import io.ktor.client.HttpClient

class EbooksApi(private val httpClient: HttpClient): IEbooksApi {

    override suspend fun getEbooks(): List<Ebook> {
        return runCatching {
            safeRequest<List<EbooksResponse>>(
                client = httpClient,
                url = "c/54f8-8f00-45f9-841e",
            ).map {
                Ebook(
                    id = it.id.orEmpty(),
                    title = it.title.orEmpty(),
                    desc = it.desc.orEmpty(),
                    cover = it.cover.orEmpty(),
                    file = it.file.orEmpty()
                )
            }
        }.getOrElse { exception ->
            throw exception
        }
    }

}