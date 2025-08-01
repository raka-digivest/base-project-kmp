package com.example.data.model.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseT<T>(
    val status: String,
    val totalResults: Int? = null,
    val articles: T? = null
)