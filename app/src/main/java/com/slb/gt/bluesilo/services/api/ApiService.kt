package com.slb.gt.bluesilo.services.api

import com.slb.gt.bluesilo.BuildConfig
import com.slb.gt.bluesilo.services.api.data.response.WikiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.androidannotations.annotations.EBean
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@EBean(scope = EBean.Scope.Singleton)
open class ApiService{

    var authToken = ""
    protected val baseUrl = "https://en.wikipedia.org/w/"
    protected val api : SlbInterface

    init {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain -> //add auth token header in all requests
            val request = chain.request().newBuilder().addHeader("token", authToken).build()
            chain.proceed(request)
        }

        if (BuildConfig.DEBUG) { //if in debug mode add logger to client
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logger) //add logger to http client
        }

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .build()
        api = retrofit.create(SlbInterface::class.java)
    }

    fun clearToken() {
        authToken = ""
    }

    fun beginSearch(srsearch: String, callback: Callback<WikiResponse.Result>) {
        val call =  api.hitCountCheck("query", "json", "search", srsearch)
        call.enqueue(callback)
    }
}