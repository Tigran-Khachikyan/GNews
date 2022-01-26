package com.gnews.data.network.response

import com.google.gson.annotations.SerializedName

data class GNewsResponse(
    @SerializedName("articles") val articles: List<ArticleResponse>?,
    @SerializedName("totalArticles") val cout: Int?
)
