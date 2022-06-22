package com.geektech.newsapp.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.geektech.newsapp.R
import com.geektech.newsapp.databinding.FragmentProfileBinding
import javax.microedition.khronos.opengles.GL


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Glide.with(binding.imageGal).load(uri).centerCrop().into(binding. imageGal)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageGal.setOnClickListener {
            getContent.launch("image/*")
        }
    }

}












