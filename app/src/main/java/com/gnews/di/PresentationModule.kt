package com.gnews.di

import com.gnews.ui.main.favourite.FavouriteViewModel
import com.gnews.ui.main.home.HomeViewModel
import com.gnews.ui.main.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(application = androidApplication()) }
    viewModel { SearchViewModel(application = androidApplication()) }
    viewModel { FavouriteViewModel(application = androidApplication()) }
}
