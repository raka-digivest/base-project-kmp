package com.example.presentation.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import baseprocject.baseprojectapp.presentation.generated.resources.Res
import baseprocject.baseprojectapp.presentation.generated.resources.compose_multiplatform
import cafe.adriel.voyager.koin.koinScreenModel
import com.example.presentation.base.BaseScreen
import org.jetbrains.compose.resources.painterResource

class NewsScreen : BaseScreen<NewsViewModel>() {
    @Composable
    override fun initViewModel(): NewsViewModel {
        return koinScreenModel<NewsViewModel>()
    }

    @Composable
    override fun buildView(viewModel: NewsViewModel) {
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
    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.White)) {
        item {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
        items(state.news) {
            Text(
                text = it.title,
                color = Color.Black
            )
        }
    }
}