package com.swensonhe.currencyconverter.di

import com.swensonhe.currencyconverter.network.ApiEndpointInterface
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(ApiEndpointInterface::class.java) }
}