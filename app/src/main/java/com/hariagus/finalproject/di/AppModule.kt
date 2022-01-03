package com.hariagus.finalproject.di

import com.hariagus.finalproject.data.source.local.LocalDataSource
import com.hariagus.finalproject.data.source.remote.RemoteDataSource
import com.hariagus.finalproject.network.NetworkClient
import org.koin.dsl.module

val appModule = module {
    single { NetworkClient.getApiService() }
    single { RemoteDataSource() }
    single { LocalDataSource(get()) }
}