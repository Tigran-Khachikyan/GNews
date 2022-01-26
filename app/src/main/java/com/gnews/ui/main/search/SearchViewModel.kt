package com.gnews.ui.main.search

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.gnews.data.network.Request
import com.gnews.domain.interactors.ArticlesInteractor
import com.gnews.ui.BaseMviViewModel
import com.gnews.ui.ScreenStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    application: Application,
    private val interactor: ArticlesInteractor
) : BaseMviViewModel<State, Effect, Event>(application) {

    private var state = State()

    override fun process(event: Event) {
        super.process(event)
        when (event) {
            is Event.Search -> {
                state.searchQuery = event.data
                searchArticleByQuery()
            }
        }
    }

    private fun searchArticleByQuery() {
        if (state.searchQuery.isNotEmpty()) {
            state.status = ScreenStatus.LOADING
            viewState = state
            viewModelScope.launch {
                val result = interactor.search(state.searchQuery, null)
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
        } else {
            state.articles = listOf()
            viewState = state
        }
    }
}
