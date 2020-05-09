package com.paula.spotyapp.Utils

import android.content.Context
import android.net.ConnectivityManager

class ValidateInternet {
    fun isInternetAvailable(context: Context):Boolean{
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}