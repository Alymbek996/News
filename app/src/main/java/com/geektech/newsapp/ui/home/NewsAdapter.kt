package com.geektech.newsapp.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.newsapp.databinding.ItemNewsBinding
import com.geektech.newsapp.models.News
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(

) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var list = arrayListOf<News>()
    var onLongClick:((pos:Int) -> Unit)? = null
    var onClick: ((news:News) ->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.BLACK)
        } else {
            holder.itemView.setBackgroundColor(Color.GRAY)
        }


        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
    fun addItem(news: News) {

        list.add(0, news)
        notifyItemInserted(0)
        notifyItemInserted(list.size -1)
         notifyItemInserted(list.indexOf(news))
    }

    fun getItem(pos: Int):News {
        return list[pos]
    }
    fun addItems(list: List<News>) {
        this.list = list as ArrayList<News>
        notifyDataSetChanged()
    }
    fun replaceItem(news: News, position: Int) {
        list.set(position, news)
        notifyItemChanged(position)
    }

    fun deleteItem(pos: Int) {
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun removeItem(news: News) {

    }


    inner class ViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.textTitle.text = news.title
            binding.textDate.text = getDate(news.createdAd, "dd MMM yyyy")
            binding.textTime.text = getDate(news.createdAd, "HH:mm,")
            itemView.setOnClickListener {
                onClick?.invoke(news)
            }

            itemView.setOnLongClickListener {
                onLongClick?.invoke(adapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun getDate(milliSeconds: Long, dateFormat: String): String {
            val formatter = SimpleDateFormat(dateFormat)
            val calendar = Calendar.getInstance();
            calendar.timeInMillis = milliSeconds;
            return formatter.format(calendar.time);
        }

    }
}