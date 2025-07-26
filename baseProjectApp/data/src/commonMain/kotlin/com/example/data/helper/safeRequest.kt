package com.example.data.helper

import com.example.data.model.base.BaseResponseT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

suspend inline fun <reified T> safeRequest(
    client: HttpClient,
    url: String,
    method: HttpMethod = HttpMethod.Get,
    body: Any? = null,
    noinline block: (HttpRequestBuilder.() -> Unit)? = null
): T {
    val response = client.request(url) {
        this.method = method
        contentType(ContentType.Application.Json)

        // Optional request body
        if (body != null) {
            setBody(body)
        }
        // Custom headers or params
        block?.let { this.apply(it) }
    }
    val baseResponse = response.body<BaseResponseT<T>>()

    if (response.status != HttpStatusCode.OK) {
        throw Exception(baseResponse.status)
    }

    return baseResponse.articles ?: throw Exception(baseResponse.status)
}