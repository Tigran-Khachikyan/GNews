package com.gnews.ui.main.favourite

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.gnews.domain.interactors.ArticlesInteractor
import com.gnews.ui.BaseMviViewModel
import com.gnews.ui.ScreenStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel(
    application: Application,
    private val interactor: ArticlesInteractor
) : BaseMviViewModel<State, Effect, Event>(application) {

    private var state = State()

    init {
        getSavedArticles()
    }

    override fun process(event: Event) {
        super.process(event)
        when (event) {
            is Event.Remove -> {
                deleteArticleFromCache(event.title)
            }
            is Event.ViewDetails -> {
                state.savedArticled.find { it.title == event.title }?.let {
                    viewEffect = Effect.NavigateTo.Details(it)
                }
            }
        }
    }

    private fun deleteArticleFromCache(title: String) {
        state.savedArticled.find { it.title == title }?.let { article ->
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

    private fun getSavedArticles() {
        state.status = ScreenStatus.LOADING
        viewState = state
        viewModelScope.launch {
            interactor.getSavedArticles().collect { result ->
                withContext(Dispatchers.Main) {
                    state.savedArticled = result
                    state.status = ScreenStatus.FETCHED
                    viewState = state
                }
            }
        }
    }
}
