package com.swensonhe.currencyconverter

import androidx.multidex.MultiDexApplication
import com.swensonhe.currencyconverter.di.apiModule
import com.swensonhe.currencyconverter.di.networkModule
import com.swensonhe.currencyconverter.di.repoModule
import com.swensonhe.currencyconverter.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class MyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                networkModule,
                apiModule,
                repoModule,
                viewModelModule
            )
        }
    }
}