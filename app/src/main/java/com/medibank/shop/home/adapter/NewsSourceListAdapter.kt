package com.medibank.shop.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.medibank.shop.R
import com.medibank.shop.common.util.AppUtility
import com.medibank.shop.home.model.Article
import com.medibank.shop.home.model.Source
import java.util.ArrayList

class NewsSourceListAdapter(
    val context: Context, private var list: List<Source>,
    val listener: OnNewsSourceListener) :
    RecyclerView.Adapter<NewsSourceListAdapter.NewsSourceListItemHolder>() {

    private var selectedSourceList : ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceListItemHolder =
        NewsSourceListItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news_source_list, parent, false)
        )

    override fun onBindViewHolder(holder: NewsSourceListItemHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    inner class NewsSourceListItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvTitle: TextView = view.findViewById(R.id.tvSourceTitle)
        private var sourceCheckBox: CheckBox = view.findViewById(R.id.checkBox)

        fun bind(model: Source) {
            tvTitle.text = model.name
            sourceCheckBox.isChecked = model.isChecked

            sourceCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                model.isChecked = isChecked
                if(isChecked) {
                    selectedSourceList.add(model.name!!)
                } else {
                    if(selectedSourceList.contains(model.name)){
                        selectedSourceList.remove(model.name)
                    }
                }
                AppUtility.setSelectedNewsSource(selectedSourceList)
            }
        }
    }

    interface OnNewsSourceListener {
        fun onNewsSourceClick(newsArticle: Article)
    }
}