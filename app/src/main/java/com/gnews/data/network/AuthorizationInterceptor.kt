package com.gnews.data.network

import com.gnews.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("token", BuildConfig.API_KEY)
            .build()
        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(url)
                .build()
        )
    }
}
