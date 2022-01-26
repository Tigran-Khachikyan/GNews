package com.gnews.ui.main.home

import com.gnews.data.network.Request
import com.gnews.domain.models.Article
import com.gnews.ui.BaseEffect
import com.gnews.ui.BaseEvent
import com.gnews.ui.BaseState

data class State(
    var articlesFromRemote: List<Article> = listOf(),
    var savedArticlesTitles: List<String> = listOf()
) : BaseState() {
    val articles: List<Article>
        get() = articlesFromRemote.onEach { item ->
            item.isFavourite = savedArticlesTitles.contains(item.title)
        }
}

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
    data class MarkAsFavourite(val title: String) : Event()
    data class RemoveFromFavourites(val title: String) : Event()
}
