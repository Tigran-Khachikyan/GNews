package com.gnews.di

import com.gnews.domain.interactors.ArticlesInteractor
import org.koin.dsl.module

val domainModule = module {
    factory { ArticlesInteractor(repository = get()) }
}
