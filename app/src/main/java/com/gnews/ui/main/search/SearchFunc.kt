package com.gnews.ui.main.search

import com.gnews.data.network.Request
import com.gnews.domain.models.Article
import com.gnews.ui.BaseEffect
import com.gnews.ui.BaseEvent
import com.gnews.ui.BaseState

data class State(
    var articles: List<Article> = listOf(),
    var searchQuery: String = ""
) : BaseState()

sealed class Effect : BaseEffect() {
    sealed class NavigateTo : Effect()
    data class ShowError(val reason: Request.Error) : Effect() {
        override val error: Request.Error
            get() = reason
    }
}

sealed class Event : BaseEvent() {
    data class Search(val data: String) : Event()
}
