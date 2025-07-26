package com.saif.cashiapp

import android.app.Application
import com.saif.cashiapp.di.KoinInitializerAndroid
import com.saif.shared.AppContext

class CashiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setInstance(this)
        AppContext.setContext(this)
        KoinInitializerAndroid(this).init()
    }

    companion object Companion {
        lateinit var instance: CashiApplication
            private set

        fun setInstance(application: CashiApplication) {
            instance = application
        }
    }

}