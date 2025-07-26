package com.saif.cashiapp

import androidx.compose.ui.window.ComposeUIViewController
import com.saif.cashiapp.di.KoinInitializerIOS

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializerIOS().init()
    }
) {
    App()
}