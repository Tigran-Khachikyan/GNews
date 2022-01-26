package com.gnews.ui.main.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.gnews.data.network.Request
import com.gnews.domain.interactors.ArticlesInteractor
import com.gnews.ui.BaseMviViewModel
import com.gnews.ui.ScreenStatus
import com.gnews.ui.main.home.Event.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class HomeViewModel(
    application: Application,
    private val interactor: ArticlesInteractor
) : BaseMviViewModel<State, Effect, Event>(application) {

    private var state = State()

    init {
        fetchArticles()
        getSavedArticles()
    }

    override fun process(event: Event) {
        super.process(event)
        when (event) {
            is MarkAsFavourite -> {
                if (event.isSelected) {
                    saveArticle(event.title)
                } else {
                    deleteArticleFromCache(event.title)
                }
            }
            is ViewDetails -> {}
            is Update -> fetchArticles()
        }
    }

    private fun saveArticle(title: String) {
        state.articles.find { it.title == title }?.let { article ->
            state.status = ScreenStatus.LOADING
            viewState = state
            viewModelScope.launch {
                interactor.saveArticle(article)
                withContext(Dispatchers.Main) {
                    state.status = ScreenStatus.FETCHED
                    viewState = state
                }
            }
        }
    }

    private fun deleteArticleFromCache(title: String) {
        state.articles.find { it.title == title }?.let { article ->
            state.status = ScreenStatus.LOADING
            viewState = state
            viewModelScope.launch {
                interactor.removeArticleFromCache(article)
                withContext(Dispatchers.Main) {
                    state.status = ScreenStatus.FETCHED
                    viewState = state
                }
            }
        }
    }

    private fun fetchArticles() {
        state.status = ScreenStatus.LOADING
        viewState = state
        viewModelScope.launch {
            val result = interactor.getTopHeadlines(null, null)
            withContext(Dispatchers.Main) {
                when (result) {
                    is Request.Success.Network -> {
                        state.articlesFromRemote = result.data.second
                    }
                    else -> {}
                }
                state.status = ScreenStatus.FETCHED
                viewState = state
            }
        }
    }

    private fun getSavedArticles() {
        state.status = ScreenStatus.LOADING
        viewState = state
        viewModelScope.launch {
            interactor.getSavedArticles().collect { result ->
                withContext(Dispatchers.Main) {
                    state.savedArticlesTitles = result.map { it.title }
                    state.status = ScreenStatus.FETCHED
                    viewState = state
                }
            }
        }
    }
}
