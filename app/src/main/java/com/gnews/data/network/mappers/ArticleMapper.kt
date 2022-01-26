package com.gnews.data.network.mappers

import com.gnews.data.network.response.ArticleResponse
import com.gnews.data.network.response.GNewsResponse
import com.gnews.domain.models.Article

object ArticleMapper : ResponseMapper<GNewsResponse, Pair<Int, List<Article>>?> {

    private val mapper by lazy {
        object : Mapper<ArticleResponse, Article> {
            override fun transform(from: ArticleResponse): Article? {
                return if (from.title != null && from.content != null) {
                    Article(
                        title = from.title,
                        content = from.content,
                        description = from.description ?: "",
                        image = from.image ?: "",
                        publishedAt = from.publishedAt ?: "unknown",
                        sourceName = from.source?.name ?: "",
                        sourceUrl = from.source?.url ?: "",
                        url = from.url ?: ""
                    )
                } else {
                    null
                }
            }
        }
    }

    override fun modelFromResponse(response: GNewsResponse): Pair<Int, List<Article>>? {
        return with(response) {
            if (cout != null && !articles.isNullOrEmpty()) {
                cout to mapper.mapSecure(articles)
            } else {
                null
            }
        }
    }
}
