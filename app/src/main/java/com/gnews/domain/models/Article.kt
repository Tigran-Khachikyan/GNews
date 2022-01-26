package com.gnews.domain.models

import com.gnews.domain.Searchable

data class Article(
    override val title: String,
    val content: String,
    val description: String,
    val image: String,
    val publishedAt: String,
    val sourceName: String,
    val sourceUrl: String,
    val url: String,
    override var isFavourite: Boolean = false
) : Searchable, Markable
