package com.gnews.domain.models

import android.os.Parcelable
import com.gnews.domain.Searchable
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Searchable, Markable, Parcelable
