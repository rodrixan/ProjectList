package com.example.rodri.projectlist.common

import com.example.rodri.projectlist.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor


object GlobalConstants {
    const val BASE_URL = "https://api.github.com/"

    val HTTP_LOGGING_LEVEL =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    const val DATE_FORMAT_BODY = "dd/MM/yyyy"
    const val DATE_FORMAT_DATA = "dd/MM/yyyy"
}