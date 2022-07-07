package com.geektech.newsapp.ui.Board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geektech.newsapp.R
import com.geektech.newsapp.databinding.PagerBoardBinding
import com.geektech.newsapp.models.ModelsPager

class BoardAdapter (private val  onClickStart: () -> Unit ): RecyclerView.Adapter<BoardAdapter.ViewHolder>() {
    private val swipe = arrayListOf<ModelsPager>(
        ModelsPager("страница1", R.drawable.news0spng,"1"),
        ModelsPager("страница2",R.drawable.news2s,"2"),
        ModelsPager("страница1",R.drawable.news4s,"3")

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
            Glide.with(binding.imageView).load(titles.image).into(binding.imageView)
            if (position == swipe.size - 1) {
               // binding.btnSkip.visibility = View.INVISIBLE
                binding.btnStart.visibility = View.VISIBLE
            } else {
//                binding.btnSkip.visibility = View.VISIBLE
                binding.btnStart.visibility = View.INVISIBLE

            }

            binding.btnStart.setOnClickListener {
                onClickStart()

            }
//            binding.btnSkip.setOnClickListener {
//                onClickStart()
//            }

        }
    }
}
