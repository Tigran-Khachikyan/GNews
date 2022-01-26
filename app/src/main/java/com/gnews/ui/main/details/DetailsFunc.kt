package com.gnews.ui.main.details

import com.gnews.data.network.Request
import com.gnews.domain.models.Article
import com.gnews.ui.BaseEffect
import com.gnews.ui.BaseEvent
import com.gnews.ui.BaseState

data class State(var article: Article? = null) : BaseState()

sealed class Effect : BaseEffect() {
    sealed class NavigateTo : Effect()
    data class ShowError(val reason: Request.Error) : Effect() {
        override val error: Request.Error
            get() = reason
    }
}

sealed class Event : BaseEvent() {
    data class InitScreen(val article: Article) : Event()
}
