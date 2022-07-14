package com.geektech.newsapp.ui.Board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.newsapp.R
import com.geektech.newsapp.databinding.PagerBoardBinding
import com.geektech.newsapp.models.ModelsPager

class BoardAdapter (private val  onClickStart: () -> Unit ): RecyclerView.Adapter<BoardAdapter.ViewHolder>() {
    private val swipe = arrayListOf<ModelsPager>(
        ModelsPager("страница1",R.raw.news_lot2,"1"),
        ModelsPager("страница2",R.raw.news_lot,"2"),
        ModelsPager("страница1",R.raw.lott,"3" )

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.ViewHolder {
        return ViewHolder(
            PagerBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoardAdapter.ViewHolder, position: Int) {
        holder.bind(swipe[position])
    }


    override fun getItemCount() = swipe.size

    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(titles: ModelsPager) {
           // binding.textTitle.text = titles[position]
            binding.textTitle.text = titles.title
            binding.textDesc.text = titles.description

            binding.imageView.setAnimation(titles.image)
         //  Glide.with(binding.imageView).load(titles.image2).into(binding.imageView)
            if (position == swipe.size - 1) {

                binding.btnStart.visibility = View.VISIBLE
            } else {

                binding.btnStart.visibility = View.INVISIBLE

            }

            binding.btnStart.setOnClickListener {
                onClickStart()

            }

        }

    }
}
