package com.swensonhe.currencyconverter.network

import com.swensonhe.currencyconverter.data.Currencies
import retrofit2.http.GET

interface ApiEndpointInterface {
    @GET("latest")
    suspend fun getCurrencies(): Currencies
}