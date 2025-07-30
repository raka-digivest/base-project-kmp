package com.example.presentation.base

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey

abstract class BaseScreen<VM : ScreenModel> : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    abstract fun initViewModel(): VM

    @Composable
    override fun Content() {
        val viewModel = initViewModel()
        buildView(viewModel)
    }


    @Composable
    abstract fun buildView(viewModel: VM)

}