package com.higor.monolithicecommerce.utils

import com.higor.monolithicecommerce.model.service.exception.ThirdPartyApiException
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import org.springframework.http.HttpStatus

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)

        if (!response.isSuccessful){
            throw ThirdPartyApiException("Please pass the correct api key")
        }

        return response
    }
}