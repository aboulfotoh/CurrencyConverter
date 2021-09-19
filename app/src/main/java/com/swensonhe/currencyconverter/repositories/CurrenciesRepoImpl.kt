package com.swensonhe.currencyconverter.repositories

import com.swensonhe.currencyconverter.data.Currencies
import com.swensonhe.currencyconverter.network.ApiEndpointInterface
import com.swensonhe.currencyconverter.utils.RemoteErrorEmitter

class CurrenciesRepoImpl(private val apiEndpointInterface: ApiEndpointInterface) : CurrenciesRepo,
    BaseRepo() {

    override suspend fun getCurrencies(emitter: RemoteErrorEmitter): Currencies? {
        val currencies = safeApiCall(emitter) {
            apiEndpointInterface.getCurrencies()
        }
        return currencies
    }
}