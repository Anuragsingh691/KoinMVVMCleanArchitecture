package com.example.swipemvvmkoin.di

import android.content.Context
import com.example.swipemvvmkoin.SwipeApiService
import com.example.swipemvvmkoin.repository.SwipeApiRepository
import com.example.swipemvvmkoin.repository.SwipeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideSwipeRepository(api: SwipeApiService, context: Context): SwipeApiRepository {
        return SwipeRepositoryImpl(api, context)
    }
    single { provideSwipeRepository(get(), androidContext()) }

}