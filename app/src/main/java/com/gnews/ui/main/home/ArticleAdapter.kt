package com.gnews.ui.main.home

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gnews.R
import com.gnews.databinding.ListItemArticleBinding
import com.gnews.domain.models.Article
import com.gnews.utils.px
import com.gnews.utils.setOnDebouncedClickListener
import com.squareup.picasso.Picasso

class ArticleAdapter(
    private val onViewContent: (String) -> Unit,
    private val onMarkAsFavourite: (String) -> Unit
) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val articlesDiffUtil by lazy {
        object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
    private val differ = AsyncListDiffer(this, articlesDiffUtil)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemArticleBinding.bind(view)
        fun bind(item: Article) {
            binding.run {
                tvTitle.text = item.title
                tvDate.text = item.publishedAt
                val icPlaceholderRes = R.drawable.ic_news
                Picasso.get()
                    .load(item.image)
                    .centerCrop(Gravity.TOP)
                    .resize(105.px, 105.px)
                    .error(icPlaceholderRes)
                    .placeholder(icPlaceholderRes)
                    .into(ivPicture)
                icFavourite.run {
                    val iconRes = if (item.isFavourite) {
                        R.drawable.ic_favorite_24
                    } else {
                        R.drawable.ic_favorite_inactive_24
                    }
                    setImageResource(iconRes)
                    setOnDebouncedClickListener {
                        onMarkAsFavourite.invoke(differ.currentList[layoutPosition].title)
                    }
                }
                layContainer.setOnDebouncedClickListener {
                    onViewContent.invoke(differ.currentList[layoutPosition].title)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_article, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun setListItems(articles: List<Article>) {
        differ.submitList(articles)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}
