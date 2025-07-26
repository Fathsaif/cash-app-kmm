package com.saif.cashiapp.di

import com.saif.cashiapp.transactions.presenter.SendPaymentViewModel
import org.koin.dsl.module

val transactionsModule = module {
    factory {
        SendPaymentViewModel(get())
    }
}