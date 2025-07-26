package com.saif.cashiapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saif.cashiapp.navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.currentKoinScope

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigation()
    }
}

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}
