package com.example.rodri.projectlist.common.rest.service

import com.example.rodri.projectlist.common.GlobalConstants
import com.example.rodri.projectlist.common.data.AppInternalData
import com.example.rodri.projectlist.common.rest.api.ProjectApi
import com.example.rodri.projectlist.common.rest.model.ProjectApiErrorResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.*


object ProjectApiService : ApiService<ProjectApi> {

    private val client: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = GlobalConstants.HTTP_LOGGING_LEVEL
        }).build()


    private val gson: Gson =
        GsonBuilder().registerTypeAdapter(
            Date::class.java,
            DateDeserializer()
        ).create()

    private var retrofitInstance: Retrofit? = null
    override val serviceRetrofit: Retrofit
        get() {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(GlobalConstants.BASE_URL)
                    .build()
            }
            return retrofitInstance!!
        }

    private var serviceApiInstance: ProjectApi? = null
    override val serviceApi: ProjectApi
        get() {
            if (serviceApiInstance == null) {
                serviceApiInstance = serviceRetrofit.create(ProjectApi::class.java)
            }
            return serviceApiInstance!!
        }

    override fun <U> serviceResponseToAppError(response: Response<U>): AppInternalData.Error {
        val converter = serviceRetrofit
            .responseBodyConverter<ProjectApiErrorResponse>(
                ProjectApiErrorResponse::class.java,
                arrayOfNulls<Annotation>(0)
            )
        response.errorBody()?.let {
            try {
                val serviceError = converter.convert(it)
                return AppInternalData.Error(serviceError?.errorMessage ?: "Null error message", response.code())
            } catch (e: Exception) {
                Timber.e("Error while parsing error response: ${e.message}")
                return AppInternalData.Error()
            }
        } ?: return AppInternalData.Error()
    }


}