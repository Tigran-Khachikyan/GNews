package com.gnews.data.db.mapper

import com.gnews.data.db.DbMapper
import com.gnews.data.db.models.ArticleEntity
import com.gnews.domain.models.Article

object ArticleDbMapper : DbMapper<Article, ArticleEntity> {
    override fun toEntity(model: Article): ArticleEntity = with(model) {
        ArticleEntity(title, content, description, image, publishedAt, sourceName, sourceUrl, url)
    }

    override fun fromEntity(entity: ArticleEntity): Article = with(entity) {
        Article(title, content, description, image, publishedAt, sourceName, sourceUrl, url, true)
    }
}
