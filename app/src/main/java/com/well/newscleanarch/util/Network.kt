package com.well.newscleanarch.util

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Network {

    fun retrofitClient(
        url: String = ApiUrlConstant.BASE_URL,
        context: Context,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient(context))
            .build()
    }


    private fun okHttpClient(context: Context): OkHttpClient {
        val httpOkClient = OkHttpClient.Builder()

        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true
        )

        return httpOkClient
            .addInterceptor(
                ChuckerInterceptor.Builder(context = context)
                    .collector(collector = chuckerCollector)
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .pingInterval(30, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
    }
}