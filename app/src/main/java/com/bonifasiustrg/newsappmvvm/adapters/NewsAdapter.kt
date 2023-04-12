package com.bonifasiustrg.newsappmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bonifasiustrg.newsappmvvm.databinding.ItemListViewBinding
import com.bonifasiustrg.newsappmvvm.models.Article
import com.bumptech.glide.Glide



class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
//    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    //This for notify adapter if data updated or change
    // so we dont need to pass list of news in parameter class (that's inefficient)
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
//            return oldItem.id == newItem.id
            //we dont compare id because actually in default API doesnt pass id for the item news,
            // the id is just in our room database
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    //assync list differ
    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
//        return ArticleViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.item_article_preview,
//                parent,
//                false
//            )
//        )
        val binding = ItemListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.ivArticleImage)
            holder.tvSource.text = article.source.name
            holder.tvTitle.text = article.title
            holder.tvDescription.text = article.description
            holder.tvPublishedAt.text = article.publishedAt
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    class ArticleViewHolder(private val binding: ItemListViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvSource: TextView = binding.tvSource
        val tvTitle: TextView = binding.tvTitle
        val tvDescription: TextView = binding.tvDescription
        val tvPublishedAt: TextView = binding.tvPublishedAt
        val ivArticleImage: ImageView = binding.ivArticleImage
    }

}