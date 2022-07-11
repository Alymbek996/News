package com.geektech.newsapp.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp.App
import com.geektech.newsapp.R
import com.geektech.newsapp.databinding.FragmentHomeBinding
import com.geektech.newsapp.models.News
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    var overwriting: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = NewsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("Home", "onViewCreated")
        binding.fub.setOnClickListener {
            overwriting = false
            findNavController().navigate(R.id.newsFragment, )

        }

        val list = App.database.newsDao().getAll()
        adapter.addItems(list)

//        parentFragmentManager.setFragmentResultListener(
//            "rk_news",
//            viewLifecycleOwner
//        ) { requestKey, bundle ->
//            val news = bundle.getSerializable("news") as News
//            val position: Int? = null
//            if (overwriting) {
//                position?.let {
//                    adapter.replaceItem(news, it)
//                }
//            } else {
//                adapter.addItem(news)
//            }
//            binding.recyclerView.adapter = adapter
//        }

        binding.recyclerView.adapter = adapter
     alertDialog()
        rewrite()
    }

    fun alertDialog() {
        adapter.onLongClick = { pos ->
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Delete Item")
//            alertDialog.setIcon(R.id)
            alertDialog.setMessage("Вы дейстивительно хотите удалить?")
                .setPositiveButton("yes", DialogInterface.OnClickListener() { _, _ ->
                    App.database.newsDao().deleteItem(adapter.getItem(pos))
                    adapter.deleteItem(pos)
                    adapter.notifyDataSetChanged()
                }).setNegativeButton("No",DialogInterface.OnClickListener { _, _ ->  })
            alertDialog.create().show()
        }
    }





    fun rewrite() {
        adapter.onClick = { news ->
            overwriting = true
            val bundle = bundleOf("news" to news)
            findNavController().navigate(R.id.newsFragment, bundle)
        }

    }

}
