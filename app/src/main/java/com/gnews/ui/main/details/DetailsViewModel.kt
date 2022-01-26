package com.gnews.ui.main.details

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.gnews.data.network.Request
import com.gnews.domain.interactors.ArticlesInteractor
import com.gnews.ui.BaseMviViewModel
import com.gnews.ui.ScreenStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(application: Application) :
    BaseMviViewModel<State, Effect, Event>(application) {

    private var state = State()

    override fun process(event: Event) {
        super.process(event)
        when (event) {
            is Event.InitScreen -> {
                state.article = event.article
                viewState = state
            }
        }
    }
}
