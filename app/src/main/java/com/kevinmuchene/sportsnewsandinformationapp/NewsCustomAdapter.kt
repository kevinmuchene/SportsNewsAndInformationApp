package com.kevinmuchene.sportsnewsandinformationapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kevinmuchene.sportsnewsandinformationapp.data.NewsData

class NewsCustomAdapter(var newsData: ArrayList<NewsData>): RecyclerView.Adapter<NewsCustomAdapter.NewsAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCustomAdapter.NewsAdapterViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_activity_details, parent, false)

        return NewsAdapterViewHolder(view)

    }

    override fun onBindViewHolder(holder: NewsCustomAdapter.NewsAdapterViewHolder, position: Int) {

        val news = newsData[position]

        Glide.with(holder.itemView.context)
            .load(news.imageUrl)
            .into(holder.newsImage)
        holder.newsTitle.text = news.title
        holder.newsDescription.text = news.description

    }

    override fun getItemCount(): Int {

        return newsData.size

    }

    inner class NewsAdapterViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val newsImage : ImageView
        val newsTitle: TextView
        val newsDescription: TextView

        init {
            newsImage = view.findViewById(R.id.newsImageId)
            newsTitle = view.findViewById(R.id.newsTitleId)
            newsDescription = view.findViewById(R.id.newsDescriptionId)
        }

    }
}