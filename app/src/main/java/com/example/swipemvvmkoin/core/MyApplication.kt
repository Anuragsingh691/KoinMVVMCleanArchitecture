package com.example.swipemvvmkoin.core

import android.app.Application
import com.example.swipemvvmkoin.di.apiModule
import com.example.swipemvvmkoin.di.networkModule
import com.example.swipemvvmkoin.di.repositoryModule
import com.example.swipemvvmkoin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule
            )
        }
    }
}
