package com.example.swipemvvmkoin.di

import com.example.swipemvvmkoin.SwipeApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideCountriesApi(retrofit: Retrofit): SwipeApiService {
        return retrofit.create(SwipeApiService::class.java)
    }
    single { provideCountriesApi(get()) }

}