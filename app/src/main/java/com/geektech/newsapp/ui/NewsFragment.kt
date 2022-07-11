package com.geektech.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp.App
import com.geektech.newsapp.databinding.FragmentNewsBinding
import com.geektech.newsapp.models.News


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private var news: News? = null
    var boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        news = arguments?.getSerializable("news") as News?

        news?.let {
            binding.editText.setText(it.title)
        }

        binding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save() {
        if (news?.title != null) {
            val newItem = News(news!!.id, binding.editText.text.toString(), System.currentTimeMillis())
            App.database.newsDao().update(newItem)
        } else {
            val text = binding.editText.text.toString().trim()
            if (news == null) {
                news = News(0, text, System.currentTimeMillis())
                App.database.newsDao().insert(news!!)
            }
        }
//                val news = News(0,text, System.currentTimeMillis())
//                val bundle = Bundle()
//                bundle.putSerializable("news", news)
//                parentFragmentManager.setFragmentResult("rk_news", bundle)


        findNavController().navigateUp()
    }
}