package com.gnews.ui.main.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gnews.R
import com.gnews.databinding.SearchItemBinding
import com.gnews.domain.Searchable
import com.gnews.utils.setOnDebouncedClickListener

class SearchableAdapter(private val onViewContent: (String) -> Unit) :
    RecyclerView.Adapter<SearchableAdapter.ViewHolder>() {

    private var items: List<Searchable> = listOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SearchItemBinding.bind(view)
        fun bind(item: Searchable) {
            binding.run {
                tvTitle.text = item.title
                root.setOnDebouncedClickListener {
                    onViewContent.invoke(items[layoutPosition].title)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setListItems(articles: List<Searchable>) {
        this.items = articles
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
