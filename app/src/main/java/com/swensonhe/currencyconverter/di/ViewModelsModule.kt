package com.swensonhe.currencyconverter.di

import com.swensonhe.currencyconverter.ui.currencies.CurrenciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CurrenciesViewModel(currenciesRepo = get())
    }
}