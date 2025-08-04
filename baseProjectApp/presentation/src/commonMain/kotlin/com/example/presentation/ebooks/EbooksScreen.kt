package com.example.presentation.ebooks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.data.ebooks.model.Ebook
import com.example.presentation.base.BaseScreen
import com.example.presentation.ebooks.reading.ReadingScreen
import com.seiko.imageloader.rememberAsyncImagePainter

class EbooksScreen : BaseScreen<EbooksViewModel>() {
    @Composable
    override fun initViewModel(): EbooksViewModel {
        return koinScreenModel<EbooksViewModel>()
    }

    @Composable
    override fun buildView(viewModel: EbooksViewModel) {
        val state by viewModel.state.collectAsState()
        val navigator: Navigator = LocalNavigator.currentOrThrow

        viewModel.sendEvent(EbooksViewEvent.LoadEbooks)

        EbooksScreenContent(state){
            navigator.push(ReadingScreen(it.file))
        }
    }

}

@Composable
fun EbooksScreenContent(state: EbooksViewState,onItemClick: (Ebook) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.ebooks) { ebook ->
            ImageCardFromUrl(imageUrl = ebook.cover){
                onItemClick(ebook)
            }
        }
    }
}

@Composable
fun ImageCardFromUrl(imageUrl: String,onItemClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.clickable {
                onItemClick()
            }
        ) {
            Image(
                painter = rememberAsyncImagePainter(url = imageUrl),
                contentDescription = "Image from URL",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}