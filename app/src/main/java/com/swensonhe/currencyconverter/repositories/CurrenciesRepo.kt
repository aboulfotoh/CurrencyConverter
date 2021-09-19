package com.swensonhe.currencyconverter.repositories

import com.swensonhe.currencyconverter.data.Currencies
import com.swensonhe.currencyconverter.utils.RemoteErrorEmitter

interface CurrenciesRepo {

    suspend fun getCurrencies(emitter: RemoteErrorEmitter): Currencies?

}