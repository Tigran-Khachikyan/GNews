package com.gnews.di

import com.gnews.data.db.AppDatabase
import com.gnews.data.db.ArticleDao
import com.gnews.data.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single { AppDatabase.create(androidApplication()) }
    single { get<AppDatabase>().dao }
}
