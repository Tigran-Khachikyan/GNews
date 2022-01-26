package com.gnews.ui.main.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.gnews.data.network.Request
import com.gnews.domain.interactors.ArticlesInteractor
import com.gnews.ui.BaseMviViewModel
import com.gnews.ui.ScreenStatus
import com.gnews.ui.main.home.Event.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    application: Application,
    private val interactor: ArticlesInteractor
) : BaseMviViewModel<State, Effect, Event>(application) {

    private var state = State()

    init {
        fetchArticles()
    }

    override fun process(event: Event) {
        super.process(event)
        when (event) {
            is MarkAsFavourite -> {}
            is ViewDetails -> {}
            is Update -> fetchArticles()
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
                        state.articles = result.data.second
                    }
                    else -> {}
                }
                state.status = ScreenStatus.FETCHED
                viewState = state
            }
        }
    }
}
