package com.example.rodri.projectlist.common

import okhttp3.logging.HttpLoggingInterceptor


object GlobalConstants {
    const val BASE_URL = "https://api.github.com/"

    val HTTP_LOGGING_LEVEL: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
    val DATE_FORMAT_BODY = "dd/MM/yyyy"
    val DATE_FORMAT_DATA = "dd/MM/yyyy"
}