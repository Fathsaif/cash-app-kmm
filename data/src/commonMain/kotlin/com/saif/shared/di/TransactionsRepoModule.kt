package com.saif.shared.di

import com.saif.shared.repository.TransactionRepository
import com.saif.shared.repository.TransactionRepositoryImpl
import com.saif.shared.repository.remoteDataSource.TransactionApi
import org.koin.dsl.module

val transactionsRepoModule = module {
    single<TransactionRepository> {
        TransactionRepositoryImpl(
            get(), get()
        )
    }
    factory {
        TransactionApi(get())
    }
    single {  }
}
