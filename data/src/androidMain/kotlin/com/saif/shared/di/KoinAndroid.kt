package com.saif.shared.di

import com.saif.shared.repository.remoteDataSource.FirestoreTransactionDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { FirestoreTransactionDataSource() }
}
