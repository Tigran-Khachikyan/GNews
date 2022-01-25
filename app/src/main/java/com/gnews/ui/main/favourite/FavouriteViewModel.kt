package com.gnews.ui.main.favourite

import android.app.Application
import com.gnews.ui.BaseMviViewModel

class FavouriteViewModel(application: Application) : BaseMviViewModel<State, Effect, Event>(application) {

    private var state = State()

    override fun process(event: Event) {
        super.process(event)
        when (event) {
        }
    }
}
