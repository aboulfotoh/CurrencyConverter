package com.swensonhe.currencyconverter.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swensonhe.currencyconverter.utils.ErrorType
import com.swensonhe.currencyconverter.utils.RemoteErrorEmitter
import com.swensonhe.currencyconverter.utils.ScreenState

open class BaseViewModel : ViewModel(), RemoteErrorEmitter {

    val mutableScreenState = MutableLiveData<ScreenState>().apply { value = ScreenState.RENDER }
    val mutableErrorMessage = MutableLiveData<String>()
    val mutableSuccessMessage = MutableLiveData<String>()
    val mutableErrorType = MutableLiveData<ErrorType>()


    override fun onError(errorType: ErrorType) {
        mutableErrorType.postValue(errorType)
    }

    override fun onError(msg: String) {
        mutableErrorMessage.postValue(msg)
    }
}