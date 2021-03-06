package com.swensonhe.currencyconverter.di

import android.content.Context
import com.swensonhe.currencyconverter.network.ApiUrls
import com.swensonhe.currencyconverter.network.ConnectivityInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { getRetrofitClient(androidContext()) }
}

private fun getRetrofitClient(androidContext: Context) =
    Retrofit.Builder()
        .baseUrl(ApiUrls.BASE_URL)
        .client(
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier { hostname, session -> true }
                .addInterceptor(ConnectivityInterceptor(androidContext))
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                    val originalHttpUrl = chain.request().url
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("access_key", ApiUrls.API_KEY)
                        .addQueryParameter("base", "EUR").build()
                    request.url(url)
                    return@addInterceptor chain.proceed(request.build())
                }
                /*.addNetworkInterceptor(Interceptor { chain ->
                    val originalResponse: Response = chain.proceed(chain.request())
                    if (UtilsHelper.isNetworkAvailable(androidContext)) {
                        val maxAge = 60 // read from cache for 1 minute
                        originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=$maxAge")
                            .build()
                    } else {
                        val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                        originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                            .build()
                    }
                })*/
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(Cache(File(androidContext.cacheDir, "okhttp_cache"), 10 * 1000 * 1000))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
