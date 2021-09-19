package com.swensonhe.currencyconverter.ui.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swensonhe.currencyconverter.data.Currencies
import com.swensonhe.currencyconverter.repositories.CurrenciesRepo
import com.swensonhe.currencyconverter.ui.BaseViewModel
import com.swensonhe.currencyconverter.utils.RemoteErrorEmitter
import com.swensonhe.currencyconverter.utils.ScreenState
import kotlinx.coroutines.launch

class CurrenciesViewModel(val currenciesRepo: CurrenciesRepo) : BaseViewModel(),
    RemoteErrorEmitter {
    private var _currencies = MutableLiveData<Currencies>()
    val currencies: LiveData<Currencies> = _currencies

    fun getCurrencies() {
        viewModelScope.launch {
            mutableScreenState.postValue(ScreenState.LOADING)
            val values = currenciesRepo.getCurrencies(this@CurrenciesViewModel)
            if (values != null)
                _currencies.postValue(values!!)

            val newState = if (values == null) ScreenState.ERROR else ScreenState.RENDER
            mutableScreenState.postValue(newState)
        }
    }
}