package com.gnews.data.network.response

import com.google.gson.annotations.SerializedName

data class SourceResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)
