package com.higor.monolithicecommerce.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient




class NetworkUtils {

    companion object {

        fun getRetrofitInstance(path: String): Retrofit {
            val httpClient = OkHttpClient().newBuilder().addInterceptor(RequestInterceptor())

            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
    }
}