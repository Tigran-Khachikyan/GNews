package com.gnews.ui.main.favourite

import com.gnews.data.network.Request
import com.gnews.domain.models.Article
import com.gnews.ui.BaseEffect
import com.gnews.ui.BaseEvent
import com.gnews.ui.BaseState

data class State(
    var savedArticled: List<Article> = listOf()
) : BaseState()

sealed class Effect : BaseEffect() {
    sealed class NavigateTo : Effect() {
        data class Details(val article: Article) : NavigateTo()
    }

    data class ShowError(val reason: Request.Error) : Effect() {
        override val error: Request.Error
            get() = reason
    }
}

sealed class Event : BaseEvent() {
    data class ViewDetails(val title: String) : Event()
    data class Remove(val title: String) : Event()
}
