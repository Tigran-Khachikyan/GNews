package com.gnews.domain.interactors

import com.gnews.data.network.Language
import com.gnews.data.network.Request
import com.gnews.data.network.Topics
import com.gnews.data.repository.ArticleRepository
import com.gnews.domain.models.Article
import kotlinx.coroutines.flow.Flow

class ArticlesInteractor(private val repository: ArticleRepository) {

    suspend fun getTopHeadlines(
        topic: Topics?,
        lang: Language?
    ): Request<Pair<Int, List<Article>>> = repository.getTopHeadlines(topic, lang)

    suspend fun search(query: String?, lang: Language?): Request<Pair<Int, List<Article>>> =
        repository.searchArticles(query, lang)

    suspend fun saveArticle(article: Article) = repository.saveArticle(article)

    suspend fun getArticleByTitle(title: String) = repository.getSavedArticleByTitle(title)

    fun getSavedArticles(): Flow<List<Article>> = repository.getSavedArticles()

    suspend fun removeArticleFromCache(article: Article) {
        repository.removeArticleFromCache(article)
    }
}
