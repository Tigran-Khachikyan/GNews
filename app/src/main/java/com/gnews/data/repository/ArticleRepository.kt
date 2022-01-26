package com.gnews.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gnews.data.db.ArticleDao
import com.gnews.data.db.mapper.ArticleDbMapper
import com.gnews.data.network.*
import com.gnews.data.network.mappers.ArticleMapper
import com.gnews.domain.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class ArticleRepository(
    private val api: NewsApi,
    private val dao: ArticleDao,
    private val coroutineContext: CoroutineContext
) {
    suspend fun getTopHeadlines(
        topic: Topics?,
        lang: Language?
    ): Request<Pair<Int, List<Article>>> =
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

    suspend fun saveArticle(article: Article) {
        dao.saveArticle(ArticleDbMapper.toEntity(article))
    }

    suspend fun removeArticleFromCache(article: Article) {
        dao.removeArticle(article.title)
    }

    suspend fun getSavedArticleByTitle(title: String): Article? {
        return dao.getArticleByTitle(title)?.let { ArticleDbMapper.fromEntity(it) }
    }

    fun getSavedArticles(): Flow<List<Article>> {
        return dao.getArticles().map { list ->
            list?.map { ArticleDbMapper.fromEntity(it) } ?: listOf()
        }
    }
}
