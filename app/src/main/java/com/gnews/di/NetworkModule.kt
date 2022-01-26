package com.gnews.di

import com.gnews.data.network.ApiProvider
import com.gnews.data.network.AuthorizationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val networkModule = module {

    single<Gson> { GsonBuilder().create() }
    single {
        HttpLoggingInterceptor().apply {
            level = (HttpLoggingInterceptor.Level.BODY)
        }
    }
    single { AuthorizationInterceptor() }
    single {
        ApiProvider(
            gson = get(),
            interceptors = listOf(
                get<HttpLoggingInterceptor>(),
                get<AuthorizationInterceptor>()
            )
        )
    }
    single { get<ApiProvider>().getMyServiceApi() }
}
