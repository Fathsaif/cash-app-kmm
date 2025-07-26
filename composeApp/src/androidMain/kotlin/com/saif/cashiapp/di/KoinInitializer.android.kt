package com.saif.cashiapp.di

import android.content.Context
import org.koin.android.ext.koin.androidContext

class KoinInitializerAndroid(
    private val context: Context
) {
    fun init() {
        KoinInitializer().init { androidContext(context) }
    }
}