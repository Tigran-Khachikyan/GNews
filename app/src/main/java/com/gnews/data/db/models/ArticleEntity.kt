package com.gnews.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ARTICLE")
class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "TITLE")
    val title: String,

    @ColumnInfo(name = "CONTENT")
    val content: String,

    @ColumnInfo(name = "DESCRIPTION")
    val description: String,

    @ColumnInfo(name = "IMAGE")
    val image: String,

    @ColumnInfo(name = "PUBLISHED_AT")
    val publishedAt: String,

    @ColumnInfo(name = "SOURCE_NAME")
    val sourceName: String,

    @ColumnInfo(name = "SOURCE_URL")
    val sourceUrl: String,

    @ColumnInfo(name = "URL")
    val url: String
)
