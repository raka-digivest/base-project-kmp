package com.example.presentation.ebooks.reading

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import com.multiplatform.webview.util.KLogSeverity
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState

data class ReadingScreen(val url: String) : Screen {
    @Composable
    override fun Content() {

        val webViewState = rememberWebViewState(url)
        webViewState.webSettings.apply {
            zoomLevel = 1.0
            isJavaScriptEnabled = true
            logSeverity = KLogSeverity.Debug
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            backgroundColor = Color.White
        }
        WebView(
            captureBackPresses = false,
            modifier = Modifier.fillMaxSize(),
            state = webViewState,
        )
    }
}