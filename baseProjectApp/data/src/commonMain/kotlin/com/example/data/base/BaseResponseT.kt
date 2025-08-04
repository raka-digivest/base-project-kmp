package com.example.data.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseT<T>(
    val ebooks: T? = null
)