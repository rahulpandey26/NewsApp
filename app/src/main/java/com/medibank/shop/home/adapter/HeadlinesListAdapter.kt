package com.medibank.shop.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.medibank.shop.R
import com.medibank.shop.home.model.Article

class HeadlinesListAdapter(val context: Context, private var list: List<Article>,
    val listener: OnNewsHeadlinesListener) : RecyclerView.Adapter<HeadlinesListAdapter.HeadlinesListItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesListItemHolder =
        HeadlinesListItemHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news_headlines_list, parent, false)
        )

    override fun onBindViewHolder(holder: HeadlinesListItemHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    inner class HeadlinesListItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var parentCardLyt: CardView = view.findViewById(R.id.cardMain)
        private var tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private var tvDesc: TextView = view.findViewById(R.id.tvDesc)
        private var tvAuthor : TextView = view.findViewById(R.id.tvAuthor)
        private var userImageView: ImageView = view.findViewById(R.id.imageView)

        fun bind(model: Article) {
            tvTitle.text = model.title
            tvDesc.text = model.description
            tvAuthor.text = model.author

            Glide.with(context)
                .load(model.urlToImage)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .dontAnimate()
                .dontTransform()
                .into(userImageView)

            parentCardLyt.setOnClickListener {listener.onNewsHeadlinesClick(model)}
        }
    }

    interface OnNewsHeadlinesListener {
          fun onNewsHeadlinesClick(newsArticle: Article)
    }
}