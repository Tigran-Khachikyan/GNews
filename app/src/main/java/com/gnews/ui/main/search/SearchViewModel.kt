package com.gnews.ui.main.search

import android.app.Application
import com.gnews.ui.BaseMviViewModel

class SearchViewModel(application: Application) : BaseMviViewModel<State, Effect, Event>(application) {

    private var state = State()

    override fun process(event: Event) {
        super.process(event)
        when (event) {
        }
    }
}
