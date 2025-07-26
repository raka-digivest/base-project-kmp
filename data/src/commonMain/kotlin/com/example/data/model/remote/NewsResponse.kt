package com.example.data.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val title: String,
    val description: String
)
