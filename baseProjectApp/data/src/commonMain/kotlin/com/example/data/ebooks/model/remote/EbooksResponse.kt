package com.example.data.ebooks.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class EbooksResponse(
    val id: String? = null,
    val title: String? = null,
    val desc:String? = null,
    val cover: String? = null,
    val file: String? = null
)
