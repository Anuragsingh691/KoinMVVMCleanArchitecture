package com.example.swipemvvmkoin.di

import com.example.swipemvvmkoin.viewModel.AddProductViewModel
import com.example.swipemvvmkoin.viewModel.CountDownViewModel
import com.example.swipemvvmkoin.viewModel.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build ProductListViewModel
    viewModel {
        ProductListViewModel(repository = get())
    }

    viewModel{
        AddProductViewModel(repository = get())
    }

    viewModel{
        CountDownViewModel()
    }

}