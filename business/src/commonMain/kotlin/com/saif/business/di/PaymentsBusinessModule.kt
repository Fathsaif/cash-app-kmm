package com.saif.business.di

import com.saif.business.transactions.GetTransactionsUseCase
import com.saif.business.transactions.SendPaymentUseCase
import org.koin.dsl.module

val paymentsBusinessModule = module {
    factory {
        SendPaymentUseCase(get())
    }
    factory {
        GetTransactionsUseCase(get())
    }
}