package com.hariagus.finalproject

import android.app.Application
import com.hariagus.finalproject.di.appModule
import com.hariagus.finalproject.di.databaseModule
import com.hariagus.finalproject.di.repositoryModule
import com.hariagus.finalproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }

}