package com.example.baseprocject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform