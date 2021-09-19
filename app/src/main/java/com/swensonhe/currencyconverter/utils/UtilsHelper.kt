package com.swensonhe.currencyconverter.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UtilsHelper : KoinComponent {

    val context: Context by inject()

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun isOnline(c: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            with(connectivityManager) {
                val networkCapabilities = getNetworkCapabilities(activeNetwork)
                return networkCapabilities != null &&
                        (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
            }
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected &&
                    (activeNetwork.type == ConnectivityManager.TYPE_WIFI || activeNetwork.type == ConnectivityManager.TYPE_MOBILE
                            || activeNetwork.type == ConnectivityManager.TYPE_VPN)
        }
    }

    fun getString(resource: Int, context: Context) = context.getString(resource)

    fun getFlag(currency: String): String {
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41
        val firstChar = Character.codePointAt(currency, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(currency, 1) - asciiOffset + flagOffset

        return (String(Character.toChars(firstChar))
                + String(Character.toChars(secondChar)))
    }

}