package cs.hse.rezunik.cosmicnews.android

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cs.hse.rezunik.cosmicnews.Item

class NewsRvAdapter(var news: List<Item> ) : RecyclerView.Adapter<NewsRvAdapter.NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
            .run(::NewsViewHolder)
    }

    override fun getItemCount(): Int = news.count()

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(news[position])
    }

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val titleTextView = itemView.findViewById<TextView>(R.id.title)
        private val timePublishedTextView = itemView.findViewById<TextView>(R.id.timePublished)
        private val articleUrlTextView = itemView.findViewById<TextView>(R.id.articleUrl)
        private val websiteTextView = itemView.findViewById<TextView>(R.id.website)
        private val imageView = itemView.findViewById<ImageView>(R.id.image)

        fun bindData(newsItem: Item) {
            val ctx = itemView.context
            titleTextView.text = newsItem.title
            Glide.with(itemView).load(newsItem.imageUrl).into(imageView)
            timePublishedTextView.text = newsItem.timePublished.split("T")[0]
            websiteTextView.text = ctx.getString(R.string.website, newsItem.newsSite ?: "")
            val url = newsItem.articleUrl

            if (url != null ) {
                articleUrlTextView.text = ctx.getString(R.string.url, newsItem.articleUrl)
                  //  launchSuccessTextView.setTextColor((ContextCompat.getColor(itemView.context, R.color.colorUnsuccessful)))
            } else {
                articleUrlTextView.text = ctx.getString(R.string.no_data)
                //articleUrlTextView.setTextColor((ContextCompat.getColor(itemView.context, R.color.colorNoData)))
            }
        }
    }
}