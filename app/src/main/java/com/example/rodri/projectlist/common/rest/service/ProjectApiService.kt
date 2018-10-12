package com.example.rodri.projectlist.common.rest.service

import com.example.rodri.projectlist.BuildConfig
import com.example.rodri.projectlist.common.GlobalConstants
import com.example.rodri.projectlist.common.rest.api.ProjectApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import java.util.*


object ProjectApiService {
    private var retrofitInstance: Retrofit? = null
    private val serviceRetrofit: Retrofit
        get() {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder()
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create(createGson()))
                    .baseUrl(GlobalConstants.BASE_URL)
                    .build()
            }
            return retrofitInstance!!
        }

    private var serviceApiInstance: ProjectApi? = null
    private val serviceApi: ProjectApi
        get() {
            if (serviceApiInstance == null) {
                serviceApiInstance = serviceRetrofit.create(
                    ProjectApi::class.java)
            }
            return serviceApiInstance!!
        }

    fun getApi(): ProjectApi =
        serviceApi

    fun getRetrofit(): Retrofit = serviceRetrofit

    private fun getClient(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }).build()


    private fun createGson(): Gson =
        GsonBuilder().registerTypeAdapter(Date::class.java,
            DateDeserializer()
        ).create()
}