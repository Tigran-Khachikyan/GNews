package com.gnews.data.network.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("content") val content: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("source") val source: SourceResponse?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?
)
