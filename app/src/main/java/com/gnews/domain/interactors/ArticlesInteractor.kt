package com.gnews.domain.interactors

import com.gnews.data.network.Language
import com.gnews.data.network.Request
import com.gnews.data.network.Topics
import com.gnews.data.repository.ArticleRepository
import com.gnews.domain.models.Article

class ArticlesInteractor(private val repository: ArticleRepository) {

    suspend fun getTopHeadlines(topic: Topics?, lang: Language?): Request<Pair<Int, List<Article>>> =
        repository.getTopHeadlines(topic, lang)

    suspend fun search(query: String?, lang: Language?): Request<Pair<Int, List<Article>>> =
        repository.searchArticles(query, lang)
}
