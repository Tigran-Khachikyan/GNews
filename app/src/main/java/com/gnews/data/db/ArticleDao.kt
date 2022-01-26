package com.gnews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gnews.data.db.models.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM ARTICLE WHERE TITLE == :title")
    suspend fun getArticleByTitle(title: String): ArticleEntity?

    @Query("SELECT * FROM ARTICLE")
    fun getArticles(): Flow<List<ArticleEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(articleEntity: ArticleEntity)

    @Query("DELETE FROM ARTICLE WHERE TITLE == :title")
    suspend fun removeArticle(title: String)
}
