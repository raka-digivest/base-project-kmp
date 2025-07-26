package com.example.presentation.news

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class NewsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<NewsViewModel>()
        val state by viewModel.state.collectAsState()
        viewModel.sendEvent(NewsViewEvent.LoadNews)

        when (val currentState = state) {
            is NewsViewState.Initial -> {
                NewsScreenContent(currentState)
            }

            is NewsViewState.Error -> {
                Text("Terjadi kesalahan: ${currentState.message}")
            }

            is NewsViewState.Loaded -> {
                NewsScreenContent(currentState)
            }
        }
    }

}

@Composable
fun NewsScreenContent(state: NewsViewState) {
    println("test state: $state")
    LazyColumn {
        items(state.news) {
            Text(
                text = it.title
            )
        }
    }
}