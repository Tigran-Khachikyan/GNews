package com.gnews

import android.app.Application
import com.gnews.data.network.ApiProvider
import com.gnews.di.domainModule
import com.gnews.di.networkModule
import com.gnews.di.presentationModule
import com.gnews.di.repositoriesModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        (get<ApiProvider>()).init()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    domainModule,
                    repositoriesModule,
                    presentationModule
                )
            )
        }
    }
}
