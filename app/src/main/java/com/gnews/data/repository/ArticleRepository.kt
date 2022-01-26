package com.gnews.data.repository

import com.gnews.data.network.*
import com.gnews.data.network.mappers.ArticleMapper
import com.gnews.domain.models.Article
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class ArticleRepository(
    private val api: NewsApi,
    private val coroutineContext: CoroutineContext
) {
    suspend fun getTopHeadlines(topic: Topics?, lang: Language?): Request<Pair<Int, List<Article>>> =
        try {
            val response = withContext(coroutineContext) {
                api.getTopHeadlines(topic?.title, lang?.title)
            }
            getMappedResponse(response, ArticleMapper)
        } catch (ex: Exception) {
            Timber.d("getEmployeesSync request error: %s", ex.message)
            Request.Error.ConnectionIssue
        }

    suspend fun searchArticles(query: String?, lang: Language?): Request<Pair<Int, List<Article>>> =
        try {
            val response = withContext(coroutineContext) {
                api.searchArticles(query, lang?.title)
            }
            getMappedResponse(response, ArticleMapper)
        } catch (ex: Exception) {
            Timber.d("getEmployeesSync request error: %s", ex.message)
            Request.Error.ConnectionIssue
        }
}
