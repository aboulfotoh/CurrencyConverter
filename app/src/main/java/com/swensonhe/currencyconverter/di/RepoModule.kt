package com.swensonhe.currencyconverter.di

import com.swensonhe.currencyconverter.repositories.CurrenciesRepo
import com.swensonhe.currencyconverter.repositories.CurrenciesRepoImpl
import org.koin.dsl.module

val repoModule = module {
    single {
        CurrenciesRepoImpl(apiEndpointInterface = get()) as CurrenciesRepo
    }
}